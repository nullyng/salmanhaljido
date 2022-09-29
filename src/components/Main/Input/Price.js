import { useEffect, useState } from "react";

import CustomTooltip from "components/common/CustomTooltip";
import CustomSlider from "components/Main/Input/CustomSlider";
import valueList from "./valueList";
import { useDispatch, useSelector } from "react-redux";
import { setPrice } from "modules/input";

function Price() {
  const minDistance = 1;

  const tooltipMessage = "가격대를 설정해보세요.";

  const dispatch = useDispatch();
  const price = useSelector((state) => state.input.price);
  const onSetPrice = (price) => dispatch(setPrice(price));

  const handleChangeJeonse = (event, newValue, activeThumb) => {
    const jeonse = price["jeonse"];
    let newJeonse = [];

    if (!Array.isArray(newValue)) {
      return;
    }
    if (activeThumb === 0) {
      newJeonse = [Math.min(newValue[0], jeonse[1] - minDistance), jeonse[1]];
    } else {
      newJeonse = [jeonse[0], Math.max(newValue[1], jeonse[0] + minDistance)];
    }
    onSetPrice({ ...price, jeonse: newJeonse });
  };

  const handleChangeMaemae = (event, newValue, activeThumb) => {
    const maemae = price["maemae"];
    let newMaemae = [];

    if (!Array.isArray(newValue)) {
      return;
    }
    if (activeThumb === 0) {
      newMaemae = [Math.min(newValue[0], maemae[1] - minDistance), maemae[1]];
    } else {
      newMaemae = [maemae[0], Math.max(newValue[1], maemae[0] + minDistance)];
    }
    onSetPrice({ ...price, maemae: newMaemae });
  };

  return (
    <div className="price">
      <div className="price__title">
        <h2 className="price__title__text">가격대 설정</h2>
        <div className="price__title__tooltip">
          <CustomTooltip content={tooltipMessage} />
        </div>
      </div>
      <div className="price__content">
        <div className="price__content__jeonse">
          <h3>평균 전세 가격</h3>
          <div className="custom-slider">
            <CustomSlider
              value={price["jeonse"]}
              onChange={handleChangeJeonse}
              valueLabelDisplay="on"
              disableSwap
              min={0}
              max={28}
              valueLabelFormat={(label) => valueList[label]}
            />
          </div>
        </div>
        <div className="price__content__maemae">
          <h3>평균 매매 가격</h3>

          <div className="custom-slider">
            <CustomSlider
              value={price["maemae"]}
              onChange={handleChangeMaemae}
              valueLabelDisplay="on"
              disableSwap
              min={0}
              max={28}
              valueLabelFormat={(label) => valueList[label]}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Price;
