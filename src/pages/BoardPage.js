import { useSelector } from "react-redux";

import "styles/Board/BoardPage.scss";
import ArticleList from "components/Board/Article/ArticleList";
import Logo from "components/common/Logo";
import Search from "components/Board/Search/Search";
import SearchResult from "components/Board/Search/SearchResult";

function BoardPage() {
  const search = useSelector((state) => state.search.search);

  return (
    <div>
      <div className="top-wrapper">
        <Logo />
        <Search />
      </div>
      {search ? <SearchResult /> : <ArticleList />}
    </div>
  );
}

export default BoardPage;
