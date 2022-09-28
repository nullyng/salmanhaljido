import { useState } from "react";

import CustomTooltip from "components/common/CustomTooltip";
import CustomSlider from "components/Main/Input/CustomSlider";
import valueList from "./valueList";

function Price() {
  const [jeonse, setJeonse] = useState([0, 28]);
  const [maemae, setMaemae] = useState([0, 28]);
  const minDistance = 1;

  const tooltipMessage = "가격대를 설정해보세요.";

  const handleChangeJeonse = (event, newValue, activeThumb) => {
    if (!Array.isArray(newValue)) {
      return;
    }
    if (activeThumb === 0) {
      setJeonse([Math.min(newValue[0], jeonse[1] - minDistance), jeonse[1]]);
    } else {
      setJeonse([jeonse[0], Math.max(newValue[1], jeonse[0] + minDistance)]);
    }
  };

  const handleChangeMaemae = (event, newValue, activeThumb) => {
    if (!Array.isArray(newValue)) {
      return;
    }
    if (activeThumb === 0) {
      setMaemae([Math.min(newValue[0], maemae[1] - minDistance), maemae[1]]);
    } else {
      setMaemae([maemae[0], Math.max(newValue[1], maemae[0] + minDistance)]);
    }
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
              value={jeonse}
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
              value={maemae}
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