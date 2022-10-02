import CategorySlider from "components/Main/Input/Category/CategorySlider";

function CategoryItem({ categoryName, categoryValue }) {
  return (
    <div className="category-item">
      <p className="category-item__name">{categoryName}</p>
      <CategorySlider categoryValue={categoryValue} />
    </div>
  );
}
export default CategoryItem;
