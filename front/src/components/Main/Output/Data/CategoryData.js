import CategoryDataCard from "components/Main/Output/Data/CategoryDataCard";

function CategoryData({ categoryName, detail }) {
  return (
    <div className="category-data">
      <div className="category-data__title">{categoryName}</div>
      {Object.keys(detail).length === 0 ? (
        <p className="category-data__content--no-data">
          상세 데이터가 없습니다.
        </p>
      ) : (
        <div className="category-data__content--data">
          {Object.keys(detail).map((item, index) => (
            <CategoryDataCard
              key={index}
              name={item}
              count={detail[item]}
              index={index}
            />
          ))}
        </div>
      )}
    </div>
  );
}
export default CategoryData;
