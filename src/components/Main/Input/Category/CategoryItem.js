import CategorySlider from "components/Main/Input/Category/CategorySlider";

function CategoryItem({ categoryName }) {
  return (
    <div className="category-item">
      <p className="category-item__name">{categoryName}</p>
      <CategorySlider categoryName={categoryName} />
    </div>
  );
}
export default CategoryItem;
