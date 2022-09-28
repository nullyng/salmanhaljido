import CustomTooltip from "components/common/CustomTooltip";
import CategoryChip from "./CategoryChip";

function MyCategory() {
    const tooltipMessage =
    "내가 현재 선택한 카테고리를 볼 수 있어요.";

    

    return(
        <div className="category">
            <div className="user-location__title">
                <h2 className="user-info__title__text">내 카테고리</h2>
                <div className="user-location__title__tooltip">
                    <CustomTooltip content={tooltipMessage} />
                </div>
            </div>
            <CategoryChip/>
        </div>
    )
 }

export default MyCategory;