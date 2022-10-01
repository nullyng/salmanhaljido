import { useSelector } from "react-redux";
import { useEffect, useRef } from "react";

import CategoryDataCard from "components/Main/Output/Data/CategoryDataCard";

function CategoryData({ categoryName, categories }) {
  const statistics = useSelector((state) => state.region.statistics);
  const dom = useRef();

  return (
    <div className="category-data" ref={dom}>
      <div className="category-data__title">{categoryName}</div>
      <div className="category-data__content">
        {categories.map((item, index) => (
          <CategoryDataCard key={index} item={item} dom={dom} />
        ))}
      </div>
    </div>
  );
}
export default CategoryData;
