import { useEffect, useRef } from "react";

import useScrollCount from "./useScrollCount";

function CategoryDataCard({ item, dom }) {
  const el = useRef();
  const animatedItem = useScrollCount(dom, 15);

  useEffect(() => {
    console.log(dom);
  });

  return (
    <div className="category-data-card">
      <div className="category-data-card__title">{Object.values(item)[0]}</div>
      <div className="category-data-card__content">
        <div></div>
        <p className="category-data-card__content__cnt" {...animatedItem}>
          0
        </p>
        <span>개</span>
      </div>
    </div>
  );
}

export default CategoryDataCard;
