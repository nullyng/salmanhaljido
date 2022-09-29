import { useState } from "react";
import { useDispatch } from "react-redux";
import { addCategory, delCategory } from "modules/CategorySet";
import Box from "@mui/material/Box";
import Slider from "@mui/material/Slider";

const valueList = ["선택안함", "하", "중", "상"];

const label = {
  1: "하",
  2: "중",
  3: "상",
};

function CategorySlider({ CategoryName }) {
  const [value, setValue] = useState(0);
  const dispatch = useDispatch();

  // value 변화가 일어나면
  const changeValue = (event, newvalue) => {
    console.log(newvalue);
    // 변한 값으로 재설정
    setValue(newvalue);
    // 변한값이 상, 중, 하(3, 2, 1)이면 리덕스 이용해서 칩 추가
    if (newvalue !== 0) {
      dispatch(addCategory(CategoryName, label[newvalue]));
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
        value={value}
        onChange={changeValue}
        marks
        min={0}
        max={3}
      />
    </Box>
  );
}
export default CategorySlider;
