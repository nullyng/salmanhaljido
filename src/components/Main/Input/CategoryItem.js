import CategorySlider from "./CategorySlider";

function CategoryItem ({CategoryName}) {    
    return (
        <div className="category__box--row">{CategoryName}<CategorySlider CategoryName={CategoryName} /></div>
    )
}
export default CategoryItem;