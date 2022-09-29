import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import Box from "@mui/material/Box";
import Slider from "@mui/material/Slider";

import { addCategory, delCategory } from "modules/category";

const valueList = ["선택안함", "하", "중", "상"];

const label = {
  1: "하",
  2: "중",
  3: "상",
};

function CategorySlider({ CategoryName }) {
  const [value, setValue] = useState(0);
  const myCategoryList = useSelector((state) => state.category.myCategoryList);
  const dispatch = useDispatch();

  // value 변화가 일어나면
  const changeValue = (event, newValue) => {
    // 변한 값으로 재설정
    setValue(newValue);
    // 변한값이 상, 중, 하(3, 2, 1)이면 리덕스 이용해서 칩 추가
    if (newValue !== 0) {
      dispatch(addCategory(CategoryName, newValue));
      // 0 이면 삭제
    } else {
      dispatch(delCategory(CategoryName));
    }
  };

  return (
    <Box sx={{ width: 150 }}>
      <Slider
        defaultValue={0}
        valueLabelFormat={(label) => valueList[label]}
        step={1}
        valueLabelDisplay="auto"
        value={myCategoryList[CategoryName] || 0}
        onChange={changeValue}
        marks
        min={0}
        max={3}
      />
    </Box>
  );
}
export default CategorySlider;
