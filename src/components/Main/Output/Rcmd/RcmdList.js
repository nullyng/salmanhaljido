import CustomTooltip from "components/common/CustomTooltip";
import RcmdChip from "components/Main/Output/Rcmd/RcmdChip.js";
import "styles/Main/Output.scss";

function RcmdList() {
  const tooltipMessage =
    "추천 지역 검색을 통해 추천 지역을 확인할 수 있어요.\n추천 지역은 순위가 높은 순으로 표시됩니다.\n해당 지역의 자세한 정보를 보고 싶다면, 원하는 지역을 클릭해 보세요!";
  return (
    <div className="region">
      <div className="region__title">
        <h2 className="region__title__text">추천 지역</h2>
        <div className="region__title__tooltip">
          <CustomTooltip content={tooltipMessage} />
        </div>
      </div>
      <div className="region__cntr">
        {/* 아마 내가 설정한 지역의 추천 */}
        <div>
          <RcmdChip />
          <RcmdChip />
          <RcmdChip />
          <RcmdChip />
          <RcmdChip />
        </div>
        {/* 설정 지역 이외 추천 지역 */}
        <div>
          <RcmdChip />
          <RcmdChip />
          <RcmdChip />
          <RcmdChip />
          <RcmdChip />
        </div>
      </div>
    </div>
  );
}

export default RcmdList;
