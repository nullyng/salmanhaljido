import { useSelector } from "react-redux";

import CategoryDataCard from "components/Main/Output/Data/CategoryDataCard";

function CategoryData({ categoryName, categories }) {
  const statistics = useSelector((state) => state.region.statistics);

  return (
    <div className="category-data">
      <div className="category-data__title">{categoryName}</div>
      <div className="category-data__content">
        {categories.map((item, index) => (
          <CategoryDataCard key={index} item={item} index={index} />
        ))}
      </div>
    </div>
  );
}
export default CategoryData;
