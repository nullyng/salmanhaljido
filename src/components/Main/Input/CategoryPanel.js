import CategoryItem from "components/Main/Input/CategoryItem";

function CategoryPanel({ arr }) {
  return (
    <div className="category__box">
      <div className="category__box--top">
        <div style={{ width: 90 }}></div>
        <div>&nbsp;&nbsp;선택안함</div>
        <div>하&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div>&nbsp;중&nbsp;&nbsp;&nbsp;</div>
        <div>&nbsp;상</div>
      </div>
      {arr.length === 0 ? (
        <p>해당 카테고리는 세부항목이 없습니다.</p>
      ) : (
        <div>
          {arr.map((item, index) => (
            <CategoryItem key={index} CategoryName={item} />
          ))}
        </div>
      )}
    </div>
  );
}
export default CategoryPanel;
