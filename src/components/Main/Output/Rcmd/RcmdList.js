import RcmdChip from "components/Main/Output/Rcmd/RcmdChip.js";
import "styles/Main/Output.scss";

function RcmdList() {
  return (
    <div className="region">
      <h2>추천 지역</h2>
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
