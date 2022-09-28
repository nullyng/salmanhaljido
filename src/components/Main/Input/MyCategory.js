import { useSelector } from "react-redux";
import CustomTooltip from "components/common/CustomTooltip";
import CategoryChip from "./CategoryChip";

function MakeChip(dic) {
    if (Object.keys(dic).length === 0) {
        return ("아직 선택된 카테고리가 없어요")
    }
    else {
        const result = []
        for (var i in dic) {
            result.push(<CategoryChip CategoryName={i} CategoryLevel={dic[i]} />)
        }
        return result
    }
}

function MyCategory() {
    const tooltipMessage =
    "내가 현재 선택한 카테고리를 볼 수 있어요.";
    
    const { MyCategoryList } = useSelector((state) => state.CategorySet);
    return(
        <div className="category">
            <div className="user-location__title">
                <h2 className="user-info__title__text">내 카테고리</h2>
                <div className="user-location__title__tooltip">
                    <CustomTooltip content={tooltipMessage} />
                </div>
            </div>
            <div className="mycategory">
                {MakeChip(MyCategoryList)}
            </div>
        </div>
    )
 }

export default MyCategory;