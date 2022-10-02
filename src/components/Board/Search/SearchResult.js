import { IconButton, Pagination, ThemeProvider, Tooltip } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useCallback, useEffect, useState } from "react";

import theme from "components/common/theme";
import ArticleCard from "components/Board/Article/ArticleCard";
import { setNews, setTotalCount } from "modules/search";
import { getSearchBoard } from "api/board";
import { ArrowBack } from "@mui/icons-material";

export const newsCategory = {
  PREGNANT_AND_DELIVERY: "임신/출산",
  PARENTING_AND_EDUCATION: "육아/교육",
  LIFE_AND_HEALTH: "생활/건강",
  SOCIAL_AND_POLICY: "사회/정책",
  REAL_ESTATE: "부동산",
};

function SearchResult() {
  const input = useSelector((state) => state.search.input);
  const category = useSelector((state) => state.search.category);
  const news = useSelector((state) => state.search.news);
  const totalCount = useSelector((state) => state.search.totalCount);

  const dispatch = useDispatch();
  const onSetNews = (news) => dispatch(setNews(news));
  const onSetTotalCount = (totalCount) => dispatch(setTotalCount(totalCount));

  // 페이지네이션
  const [currentPage, setCurrentPage] = useState(1);

  const onPageChange = (e, page) => {
    setCurrentPage(page);
  };

  // 뉴스 api 요청
  const fetchBoard = useCallback(() => {
    getSearchBoard(category, currentPage - 1, input, (res) => {
      onSetNews(res.data.newsList);
      onSetTotalCount(res.data.totalCount);
    });
  }, [currentPage]);

  // 뒤로 가기 버튼 클릭 시 전체 게시글 페이지로 이동
  const handleBack = () => {
    window.location.reload();
  };

  useEffect(() => {
    fetchBoard();
  }, [currentPage, fetchBoard]);

  return (
    <div className="search-result">
      <div className="search-result__wrapper">
        <div className="search-result__wrapper__top">
          <div className="search-result__wrapper__top--title">
            <IconButton onClick={handleBack}>
              <ArrowBack />
            </IconButton>
            <div>
              <span>{newsCategory[category]}</span>의 <span>"{input}"</span>
              &nbsp;검색 결과
            </div>
          </div>
          <div className="search-result__wrapper__top--count">
            {totalCount}개
          </div>
        </div>
        {news.length === 0 ? (
          <p className="search-result__wrapper__content--no-data">
            검색 결과가 없습니다.
          </p>
        ) : (
          <div>
            <div className="search-result__wrapper__content">
              {news.map((value, idx) => (
                <ArticleCard key={idx} {...value} />
              ))}
            </div>
            <ThemeProvider theme={theme}>
              <Pagination
                count={Math.ceil(totalCount / 8)}
                page={currentPage}
                color="secondary"
                className="pagenation"
                onChange={onPageChange}
              />
            </ThemeProvider>
          </div>
        )}
      </div>
    </div>
  );
}

export default SearchResult;
