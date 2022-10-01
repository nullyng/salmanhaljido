import CategoryData from "./CategoryData";

function DetailData({ statistics }) {
  return (
    <div className="detail-data">
      <div>
        <CategoryData statistics={statistics} />
      </div>
    </div>
  );
}

export default DetailData;
