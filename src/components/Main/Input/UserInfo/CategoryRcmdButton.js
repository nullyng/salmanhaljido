import { Button, Collapse, Divider } from "@mui/material";
import { getCategoryRcmd } from "api/category";
import { useEffect, useState } from "react";
import CategoryRcmd from "./CategoryRcmd";

function CategoryRcmdButton({ isMarried, hasCar, hasPets, hasChildren, open }) {
  const [countOpen, setCountOpen] = useState(false);
  const [ratingOpen, setRatingOpen] = useState(false);
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

  const handleClickCount = () => {
    apiData = { ...apiData, standard: false };
    console.log(apiData);

    getCategoryRcmd(apiData, (res) => {
      console.log(res.data);
    });

    setCountOpen(true);
    setRatingOpen(false);
  };

  const handleClickRating = () => {
    apiData = { ...apiData, standard: true };
    console.log(apiData);

    getCategoryRcmd(apiData, (res) => {
      console.log(res.data);
    });

    setRatingOpen(true);
    setCountOpen(false);
  };

  return (
    <Collapse in={open}>
      <div className="category-rcmd-btn">
        <h3>
          <span>&#x1F4A1;</span>카테고리 추천 받기
        </h3>
        <p>비슷한 환경의 사람들이 선택한 카테고리를 추천 받아보세요.</p>
        <div className="category-rcmd-btn__wrapper">
          <Button variant="outlined" size="small" onClick={handleClickCount}>
            검색 횟수 기준으로 추천 받기
          </Button>
          <Button variant="outlined" size="small" onClick={handleClickRating}>
            평점 기준으로 추천 받기
          </Button>
        </div>
        {countOpen && (
          <CategoryRcmd
            title="이런 카테고리를 많이 검색했어요."
            alertOpen={countOpen}
            setAlertOpen={setCountOpen}
          />
        )}
        {ratingOpen && (
          <CategoryRcmd
            title="이런 카테고리를 많이 추천했어요."
            alertOpen={ratingOpen}
            setAlertOpen={setRatingOpen}
          />
        )}
      </div>
    </Collapse>
  );
}

export default CategoryRcmdButton;
