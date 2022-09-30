import * as React from "react";
import PropTypes from "prop-types";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import { useState, useEffect, useCallback } from "react";
import Pagination from "@mui/material/Pagination";

import Category from "components/Board/Article/Category";
import { getBoard } from "api/board";
import { newscategory } from "components/Board/Article/NewsCategory";

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

function BasicTabs() {
  // 뉴스 데이터
  const [news, setNews] = useState([]);

  const [value, setValue] = useState(0);
  // console.log(value);
  // console.log(newscategory[1])

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  // mui tabs 커스텀
  // 클릭된 버튼 색, ##########폰트 두께##########
  const theme = createTheme({
    palette: {
      primary: {
        main: "#e94560",
      },
      secondary: {
        main: "#0F3460",
      },
    },
  });

  const fetchBoard = useCallback(() => {
    getBoard(newscategory[value], 0, (res) => {
      setNews(res.data.newsList);
    });
  }, [value]);


  useEffect(() => {
    fetchBoard();
  }, [value, fetchBoard]);

  return (
    <ThemeProvider theme={theme}>
      <div className="board">
        <Box sx={{ width: "100%" }}>
          <Box>
            <Tabs
              value={value}
              onChange={handleChange}
              aria-label="basic tabs example"
              className="board__tabs"
            >
              <Tab label="임신/출산" {...a11yProps(0)} />
              <Tab label="육아/교육" {...a11yProps(1)} />
              <Tab label="생활/건강" {...a11yProps(2)} />
              <Tab label="사회/정책" {...a11yProps(3)} />
              <Tab label="부동산" {...a11yProps(4)} />
            </Tabs>
          </Box>

          <TabPanel value={value} index={0}>
            <Category news={news} />
          </TabPanel>
          <TabPanel value={value} index={1}>
            <Category news={news} />
          </TabPanel>
          <TabPanel value={value} index={2}>
            <Category news={news} />
          </TabPanel>
          <TabPanel value={value} index={3}>
            <Category news={news} />
          </TabPanel>
          <TabPanel value={value} index={4}>
            <Category news={news} />
          </TabPanel>
          <Pagination count={10} color="secondary" className="pagenation" />
        </Box>
      </div>
    </ThemeProvider>
  );
}

export default BasicTabs;
