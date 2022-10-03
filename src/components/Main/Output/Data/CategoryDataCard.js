import { useEffect } from "react";

import useScrollCount from "hooks/useScrollCount";

function CategoryDataCard({ name, count, index }) {
  const animatedItem = useScrollCount(count);

  useEffect(() => {
    // 컴포넌트가 리렌더링 되었을 때도 카운트 애니메이션하도록 함
    const card = document.querySelector(
      `.category-data-card__content__cnt-${index}`
    );
    counting(card, count);
  });

  const counting = (card, end, start = 0, duration = 1000) => {
    const stepTime = Math.abs(Math.floor(duration / (end - start)));

    let currentNumber = start;
    const counter = setInterval(() => {
      currentNumber += 1;
      card.innerText = currentNumber;

      if (currentNumber === end) {
        clearInterval(counter);
      }
    }, stepTime);
  };

  return (
    <div className="category-data-card">
      <div className="category-data-card__title">{name}</div>
      <div className="category-data-card__content">
        <div></div>
        <p
          className={`category-data-card__content__cnt-${index}`}
          {...animatedItem}
        >
          0
        </p>
        <span>개</span>
      </div>
    </div>
  );
}

export default CategoryDataCard;
