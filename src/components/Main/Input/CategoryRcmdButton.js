import { Button, Collapse } from "@mui/material";
import { useEffect, useState } from "react";
import CategoryRcmd from "./CategoryRcmd";

function CategoryRcmdButton({ hasCar, hasPets, hasChildren, open }) {
  const [countOpen, setCountOpen] = useState(false);
  const [ratingOpen, setRatingOpen] = useState(false);

  useEffect(() => {
    console.log(hasCar + " " + hasPets + " " + hasChildren);
    setCountOpen(false);
    setRatingOpen(false);
  }, [hasCar, hasPets, hasChildren]);

  const handleClickCount = () => {
    // api 통신
    setCountOpen(true);
    setRatingOpen(false);
  };

  const handleClickRating = () => {
    // api 통신
    setRatingOpen(true);
    setCountOpen(false);
  };

  return (
    <Collapse in={open}>
      <div className="category-rcmd-btn">
        <p>비슷한 환경의 사람들이 선택한 카테고리를 추천받아 보세요.</p>
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
