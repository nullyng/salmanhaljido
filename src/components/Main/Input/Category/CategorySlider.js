import { useDispatch, useSelector } from "react-redux";
import Slider from "@mui/material/Slider";

import { addCategory, delCategory } from "modules/category";

const valueList = ["선택안함", "하", "중", "상"];

function CategorySlider({ categoryName }) {
  const myCategoryList = useSelector((state) => state.category.myCategoryList);
  const dispatch = useDispatch();

  // value 변화가 일어나면
  const changeValue = (event, newValue) => {
    // 변한값이 상, 중, 하(3, 2, 1)이면 리덕스 이용해서 칩 추가
    if (newValue !== 0) {
      dispatch(addCategory(categoryName, newValue));
      // 0 이면 삭제
    } else {
      dispatch(delCategory(categoryName));
    }
  };

  return (
    <div className="category-slider">
      {Object.keys(myCategoryList).includes(categoryName) ? (
        <Slider
          className="category-slider--selected"
          defaultValue={0}
          valueLabelFormat={(label) => valueList[label]}
          step={1}
          valueLabelDisplay="auto"
          value={myCategoryList[categoryName] || 0}
          onChange={changeValue}
          marks
          min={0}
          max={3}
        />
      ) : (
        <Slider
          className="category-slider--no-select"
          defaultValue={0}
          valueLabelFormat={(label) => valueList[label]}
          step={1}
          valueLabelDisplay="auto"
          value={myCategoryList[categoryName] || 0}
          onChange={changeValue}
          marks
          min={0}
          max={3}
        />
      )}
    </div>
  );
}
export default CategorySlider;
