import CategoryData from "components/Main/Output/Data/CategoryData";

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

function DetailData({ category, detail }) {
  return (
    <div className="detail-data">
      <div>
        <CategoryData categoryName={categoryName[category]} detail={detail} />
      </div>
    </div>
  );
}

export default DetailData;
