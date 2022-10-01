import { useEffect, useState } from "react";

import CategoryData from "components/Main/Output/Data/CategoryData";
import { categoryList } from "components/Main/Input/Category/categoryList";

function DetailData({ category }) {
  const categoryName = {
    0: "교통",
    1: "재난",
    2: "안전",
    3: "의료",
    4: "반려동물",
    5: "교육",
    6: "문화",
    7: "생활",
  };
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    setCategories(categoryList[categoryName[category]]);
  }, [category]);

  return (
    <div className="detail-data">
      <div>
        <CategoryData
          categoryName={categoryName[category]}
          categories={categories}
        />
      </div>
    </div>
  );
}

export default DetailData;
