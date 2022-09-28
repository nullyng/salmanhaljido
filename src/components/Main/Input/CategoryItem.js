import { useDispatch } from "react-redux";
import { addCategory } from "modules/CategorySet"
import CategorySlider from "./CategorySlider";

function CategoryItem ({CategoryName}) {
    const dispatch = useDispatch();
    const add = () => {dispatch(addCategory(CategoryName, "상"))};
    return (
        <div className="category__box--row" onClick={add}>{CategoryName}<CategorySlider/></div>
    )
}
export default CategoryItem;