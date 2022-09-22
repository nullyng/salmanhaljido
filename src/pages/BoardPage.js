import Paper from '@mui/material/Paper';
import InputBase from '@mui/material/InputBase';
import IconButton from '@mui/material/IconButton';
import SearchIcon from '@mui/icons-material/Search';

import "styles/Board/BoardPage.scss";
import ArticleList from "components/Board/Article/ArticleList";
import Logo from "components/common/Logo";

function BoardPage() {
  return (
    <div>
      <div className="top-wrapper">
        <Logo />
        <div className="search">
          <Paper
          className='search__bar'
          component="form"
          sx={{ p: '2px 4px', display: 'flex', alignItems: 'center', width: 400 }}
          >
            <SearchIcon className='search__icon' />
            <InputBase
              sx={{ ml: 1, flex: 1 }}
              placeholder="검색어를 입력하세요"
              inputProps={{ 'aria-label': 'search google maps' }}

            />
          </Paper>
          <IconButton type="button" sx={{ p: '10px' }} aria-label="search">
            검색
          </IconButton>
        </div>
      </div>
      
      <ArticleList />
    </div>
  );
}

export default BoardPage;
