import InputBase from "@mui/material/InputBase";
import SearchIcon from "@mui/icons-material/Search";
import { useDispatch } from "react-redux";
import { useState } from "react";
import {
  Button,
  createTheme,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  Snackbar,
  ThemeProvider,
} from "@mui/material";

import { getSearchBoard } from "api/board";
import {
  setCategory,
  setInput,
  setNews,
  setSearch,
  setTotalCount,
} from "modules/search";

const theme = createTheme({
  palette: {
    primary: {
      main: "#e94560",
    },
  },
});

function Search() {
  const [searchInput, setSearchInput] = useState("");
  const [searchCategory, setSearchCategory] = useState("");

  // 카테고리 설정이 안되었거나 검색어가 없을 때 출력할 스낵바
  const [categorySnackbar, setCategorySnackbar] = useState(false);
  const [inputSnackbar, setInputSnackbar] = useState(false);

  const dispatch = useDispatch();
  const onSetInput = (search) => dispatch(setInput(search));
  const onSetCategory = (category) => dispatch(setCategory(category));
  const onSetNews = (news) => dispatch(setNews(news));
  const onSetSearch = (search) => dispatch(setSearch(search));
  const onSetTotalCount = (totalCount) => dispatch(setTotalCount(totalCount));

  const handleChangeCategory = (event) => {
    setSearchCategory(event.target.value);
  };

  const handleChangeInput = (event) => {
    setSearchInput(event.target.value);
  };

  const handleSubmit = () => {
    if (searchCategory.length === 0) {
      setCategorySnackbar(true);
    } else if (searchInput.length === 0) {
      setInputSnackbar(true);
    } else {
      onSetCategory(searchCategory);
      onSetInput(searchInput);

      getSearchBoard(searchCategory, 0, searchInput, (res) => {
        onSetNews(res.data.newsList);
        onSetTotalCount(res.data.totalCount);
        onSetSearch(true);
      });
    }
  };

  return (
    <div className="search">
      <ThemeProvider theme={theme}>
        <div className="search__category">
          <FormControl fullWidth size="small">
            <InputLabel id="demo-simple-select-label">카테고리</InputLabel>
            <Select
              value={searchCategory}
              label="category"
              onChange={handleChangeCategory}
            >
              <MenuItem value={"PREGNANT_AND_DELIVERY"}>임신/출산</MenuItem>
              <MenuItem value={"PARENTING_AND_EDUCATION"}>육아/교육</MenuItem>
              <MenuItem value={"LIFE_AND_HEALTH"}>생활/건강</MenuItem>
              <MenuItem value={"SOCIAL_AND_POLICY"}>사회/정책</MenuItem>
              <MenuItem value={"REAL_ESTATE"}>부동산</MenuItem>
            </Select>
          </FormControl>
        </div>
        <div className="search__input">
          <SearchIcon className="search__input--icon" />
          <InputBase
            onChange={handleChangeInput}
            placeholder="검색어를 입력하세요."
          />
        </div>
        <div className="search__button">
          <Button variant="outlined" size="large" onClick={handleSubmit}>
            검색
          </Button>
        </div>
        <Snackbar
          anchorOrigin={{ vertical: "top", horizontal: "right" }}
          open={categorySnackbar}
          onClose={() => setCategorySnackbar(false)}
          message="카테고리를 설정해주세요."
          key="category"
          autoHideDuration={2000}
        />
        <Snackbar
          anchorOrigin={{ vertical: "top", horizontal: "right" }}
          open={inputSnackbar}
          onClose={() => setInputSnackbar(false)}
          message="검색어를 입력해주세요."
          key="input"
          autoHideDuration={2000}
        />
      </ThemeProvider>
    </div>
  );
}

export default Search;
