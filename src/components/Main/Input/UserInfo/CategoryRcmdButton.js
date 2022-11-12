import { Button, Collapse } from "@mui/material";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";

import CategoryRcmd from "components/Main/Input/UserInfo/CategoryRcmd";
import { getCategoryRcmd } from "api/category";
import { setLoading } from "modules/loading";

function CategoryRcmdButton({ isMarried, hasCar, hasPets, hasChildren, open }) {
  const [countOpen, setCountOpen] = useState(false);
  const [ratingOpen, setRatingOpen] = useState(false);
  const [rcmdList, setRcmdList] = useState({});

  const dispatch = useDispatch();
  const onSetLoading = (loading) => dispatch(setLoading(loading));

  let apiData = {
    married: isMarried,
    hasPets: hasPets,
    hasCar: hasCar,
    hasChildren: hasChildren,
  };

  useEffect(() => {
    // 사용자 정보가 달라지면 추천 컴포넌트 삭제
    setCountOpen(false);
    setRatingOpen(false);
  }, [isMarried, hasCar, hasPets, hasChildren]);

  // 검색 횟수 기준으로 추천 받을 때
  const handleClickCount = () => {
    apiData = { ...apiData, standard: false };

    // api 통신하는 동안 로딩 화면 출력
    onSetLoading(true);

    getCategoryRcmd(apiData, (res) => {
      // api 통신이 끝나면 로딩 화면 출력 멈춤
      onSetLoading(false);
      setRcmdList(makePriorityList(res.data.categoriesList));

      // 검색 횟수 기준의 추천 Alert 출력
      setCountOpen(true);
      setRatingOpen(false);
    });
  };

  // 평점 기준으로 추천 받을 때
  const handleClickRating = () => {
    apiData = { ...apiData, standard: true };

    onSetLoading(true);

    getCategoryRcmd(apiData, (res) => {
      onSetLoading(false);
      setRcmdList(makePriorityList(res.data.categoriesList));

      // 평점 기준의 추천 Alert 출력
      setRatingOpen(true);
      setCountOpen(false);
    });
  };

  // 상, 중, 하로 카테고리 분류
  const makePriorityList = (categoriesList) => {
    let priorityList = { low: [], middle: [], high: [] };

    categoriesList.map((item) => {
      if (item.value === "low") {
        priorityList["low"].push(item.category);
      } else if (item.value === "middle") {
        priorityList["middle"].push(item.category);
      } else {
        priorityList["high"].push(item.category);
      }
    });

    return priorityList;
  };

  return (
    <Collapse in={open}>
      <div className="category-rcmd-btn">
        <h3>
          <span>&#x1F48C;</span>카테고리 추천받기
        </h3>
        <p className="category-rcmd-btn__desc">
          비슷한 환경의 사람들이 선택한 카테고리를 추천받아 보세요.
        </p>
        <div className="category-rcmd-btn__wrapper">
          <Button className="rcmd-btn" onClick={handleClickCount}>
            <span className="circle" aria-hidden="true">
              <span className="icon arrow"></span>
            </span>
            <span className="button-text">검색 횟수 기준</span>
          </Button>
          <Button className="rcmd-btn" onClick={handleClickRating}>
            <span className="circle" aria-hidden="true">
              <span className="icon arrow"></span>
            </span>
            <span className="button-text">평점 기준</span>
          </Button>
        </div>
        {countOpen && (
          <CategoryRcmd
            title="이런 카테고리를 많이 검색했어요."
            alertOpen={countOpen}
            setAlertOpen={setCountOpen}
            rcmdList={rcmdList}
          />
        )}
        {ratingOpen && (
          <CategoryRcmd
            title="이런 카테고리를 많이 추천했어요."
            alertOpen={ratingOpen}
            setAlertOpen={setRatingOpen}
            rcmdList={rcmdList}
          />
        )}
      </div>
    </Collapse>
  );
}

export default CategoryRcmdButton;
