import CategoryData from "./CategoryData";
import CustomTooltip from "components/common/CustomTooltip";

function DetailData({ statistics }) {
  const tooltipMessage =
    "선택한 지역의 카테고리별 정보를 확인해보세요.";
  return (
    <div className="detail-data">
      <div>
        <div className="user-info__title">
          <h2 className="category__title__text">상세 데이터</h2>
          <div className="user-location__title__tooltip">
              <CustomTooltip content={tooltipMessage} />
          </div>
        </div>
        
        <CategoryData statistics={statistics} />
      </div>
    </div>
  );
}

export default DetailData;
