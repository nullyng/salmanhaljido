import Pagination from "@mui/material/Pagination";

import ArticleCard from "./ArticleCard";
import dummy from "../DummyData.json";

function CategoryOne() {
  
  return (
    <div>
      <div className="article-list">
        {dummy.map((dummy, idx) => (
          <ArticleCard key={idx} {...dummy} />
        ))}
      </div>
      <Pagination count={10} color="secondary" className="pagenation"/>
    </div>
  );
}

export default CategoryOne;
