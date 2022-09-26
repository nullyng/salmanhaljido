import Pagination from "@mui/material/Pagination";

import ArticleCard from "components/Board/Article/ArticleCard";
import dummy from "../DummyData.json";

function CategoryFour() {
  return (
    <div>
      <div className="article-list">
        {dummy.map((dummy, idx) => (
          <ArticleCard key={idx} {...dummy} />
        ))}
      </div>
      <Pagination count={10} color="secondary" className="pagenation" />
    </div>
  );
}

export default CategoryFour;
