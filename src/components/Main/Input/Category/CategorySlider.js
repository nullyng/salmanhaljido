import { useDispatch, useSelector } from "react-redux";
import Slider from "@mui/material/Slider";

import { addCategory, delCategory } from "modules/category";

const valueList = ["선택안함", "하", "중", "상"];
const valueToPriority = ["low", "middle", "high"];
const priorityToValue = { low: 1, middle: 2, high: 3 };

function CategorySlider({ categoryValue }) {
  const myCategoryList = useSelector((state) => state.category.myCategoryList);
  const dispatch = useDispatch();

  // value 변화가 일어나면
  const changeValue = (event, newValue) => {
    // 변한 값이 상, 중, 하(high, middle, low)이면 리덕스 이용해서 칩 추가
    if (newValue !== 0) {
      dispatch(addCategory(categoryValue, valueToPriority[newValue - 1]));
      // 0 이면 삭제
    } else {
      dispatch(delCategory(categoryValue));
    }
  };

  return (
    <div className="category-slider">
      {Object.keys(myCategoryList).includes(categoryValue) ? (
        <Slider
          className="category-slider--selected"
          defaultValue={0}
          valueLabelFormat={(label) => valueList[label]}
          step={1}
          valueLabelDisplay="auto"
          value={priorityToValue[myCategoryList[categoryValue]] || 0}
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
          value={0}
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
