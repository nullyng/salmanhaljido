import * as React from "react";
import PropTypes from "prop-types";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import { ThemeProvider, createTheme } from "@mui/material/styles";

import CategoryOne from "./CategoryOne";
import CategoryTwo from "./CategoryTwo";
import CategoryThree from "./CategoryThree";
import CategoryFour from "./CategoryFour";
import CategoryFive from "./CategoryFive";


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
  const [value, setValue] = React.useState(0);

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
        main: "#0F3460"
      }
    },
  });

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
            <CategoryOne />
          </TabPanel>
          <TabPanel value={value} index={1}>
            <CategoryTwo />
          </TabPanel>
          <TabPanel value={value} index={2}>
            <CategoryThree />
          </TabPanel>
          <TabPanel value={value} index={3}>
            <CategoryFour />
          </TabPanel>
          <TabPanel value={value} index={4}>
            <CategoryFive />
          </TabPanel>
        </Box>
      </div>
    </ThemeProvider>
  );
}

export default BasicTabs;
