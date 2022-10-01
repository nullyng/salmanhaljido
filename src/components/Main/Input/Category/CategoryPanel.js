import CategoryItem from "components/Main/Input/Category/CategoryItem";

function CategoryPanel({ categoryList }) {
  return (
    <div className="category-panel">
      <div className="category-panel__top">
        <div className="category-panel__top--empty"></div>
        <div className="category-panel__top--right">
          <span>하</span>
          <span>중</span>
          <span>상</span>
        </div>
      </div>
      <div className="category-panel__categories">
        {categoryList.map((item, index) => (
          <CategoryItem
            key={index}
            categoryName={Object.values(item)[0]}
            categoryValue={Object.keys(item)[0]}
          />
        ))}
      </div>
    </div>
  );
}
export default CategoryPanel;
