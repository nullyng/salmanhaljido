import ArticleCard from "components/Board/Article/ArticleCard";

function Category({ news }) {
  return (
    <div className="article-list">
      {news.map((value, idx) => (
        <ArticleCard key={idx} {...value} />
      ))}
    </div>
  );
}

export default Category;
