import { useEffect } from "react";

import CustomTooltip from "components/common/CustomTooltip";
import RcmdChip from "components/Main/Output/Rcmd/RcmdChip.js";
import "styles/Main/Output.scss";

function RcmdList({
  rcmdData,
  currRegion,
  onSetCurrRegion,
  onSetStatistics,
  onSetRealEstate,
}) {
  const tooltipMessage =
    "추천 지역 검색을 통해 추천 지역을 확인할 수 있어요.\n추천 지역은 순위가 높은 순으로 표시됩니다.\n해당 지역의 자세한 정보를 보고 싶다면, 원하는 지역을 클릭해 보세요!";

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

  const handleClick = (data) => {
    // 현재 선택한 지역, 해당 지역의 통계 및 부동산 정보 저장
    onSetCurrRegion(data);
    onSetStatistics(data.stat);
    onSetRealEstate(data.re);
  };

  return (
    <div className="region">
      <div className="region__title">
        <h2 className="region__title__text">추천 지역</h2>
        <div className="region__title__tooltip">
          <CustomTooltip content={tooltipMessage} />
        </div>
      </div>
      <div className="region__cntr">
        <div>
          <h3>선택한 지역 내</h3>
        </div>
        <div>
          <h3>선택한 지역 외</h3>
        </div>
        {rcmdData.length === 0 ? (
          <p className="region__cntr--no-data">추천 지역을 검색해주세요.</p>
        ) : (
          <div className="region__cntr--data">
            {rcmdData.map((data, index) => {
              return (
                <RcmdChip
                  key={index}
                  index={index}
                  name={data.name}
                  code={data.code}
                  score={data.score}
                  handleClick={() => handleClick(data)}
                />
              );
            })}
          </div>
        )}
      </div>
    </div>
  );
}

export default RcmdList;
