import { useSelector } from "react-redux";

import CustomTooltip from "components/common/CustomTooltip";
import CategoryChip from "components/Main/Input/CategoryChip";

function MyCategory() {
  const tooltipMessage = "내가 현재 선택한 카테고리를 볼 수 있어요.";

  const myCategoryList = useSelector((state) => state.category.myCategoryList);

  return (
    <div className="my-category">
      <div className="my-category__title">
        <h2 className="my-category__title__text">내 카테고리</h2>
        <div className="my-category__title__tooltip">
          <CustomTooltip content={tooltipMessage} />
        </div>
      </div>
      <div className="my-category__chips">
        {Object.keys(myCategoryList).length === 0 ? (
          <p>아직 선택된 카테고리가 없어요</p>
        ) : (
          <div className="my-category__chips--data">
            {Object.keys(myCategoryList).map((category, index) => (
              <CategoryChip
                key={index}
                CategoryName={category}
                CategoryLevel={myCategoryList[category]}
              />
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default MyCategory;
