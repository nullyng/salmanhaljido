import { useEffect } from "react";
import { useSelector } from "react-redux";

import CustomTooltip from "components/common/CustomTooltip";
import RcmdItem from "components/Main/Output/Rcmd/RcmdItem.js";
import "styles/Main/Output.scss";

function RcmdList() {
  const tooltipMessage =
    "추천 지역 검색을 통해 추천 지역을 확인할 수 있어요.\n추천 지역은 순위가 높은 순으로 표시됩니다.\n해당 지역의 자세한 정보를 보고 싶다면, 원하는 지역을 클릭해 보세요!";

  const region = useSelector((state) => state.input.region);
  const rcmdData = useSelector((state) => state.region.rcmdData);
  const currRegion = useSelector((state) => state.region.currRegion);

  useEffect(() => {
    let el;
    let elClassName;

    if (JSON.stringify(currRegion) === "{}") return;

    el = document.getElementsByClassName(`${currRegion.code}`)[0];
    elClassName = el.className;
    el.className += " selected";

    return () => {
      el.className = elClassName;
    };
  }, [currRegion]);

  return (
    <div className="region">
      <div className="region__title">
        <h2 className="region__title__text">추천 지역</h2>
        <div className="region__title__tooltip">
          <CustomTooltip content={tooltipMessage} />
        </div>
      </div>
      <div className="region__cntr">
        {rcmdData.length === 0 ? (
          <p className="region__cntr--no-data">추천 지역을 검색해주세요.</p>
        ) : (
          <div className="region__cntr--data">
            {region.length > 0 ? (
              <div>
                {rcmdData.map((data, index) => {
                  return <RcmdItem key={index} index={index} data={data} />;
                })}
              </div>
            ) : (
              <div>
                <div>
                  <h3>선택한 지역 내</h3>
                  {rcmdData.map((data, index) => {
                    return <RcmdItem key={index} index={index} data={data} />;
                  })}
                </div>
                <div>
                  <h3>선택한 지역 외</h3>
                  {rcmdData.map((data, index) => {
                    return <RcmdItem key={index} index={index} data={data} />;
                  })}
                </div>
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
}

export default RcmdList;
