import PropTypes from "prop-types";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";
import { useState } from "react";

import RealEstateList from "components/Main/Output/Data/RealEstateList";
import ChartData from "components/Main/Output/Data/ChartData";
import CustomTooltip from "components/common/CustomTooltip";

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
      {value === index && <Box sx={{ paddingY: 3 }}>{children}</Box>}
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

function Data({ statistics, realEstate }) {
  const [value, setValue] = useState(0);

  const tooltipMessage = "선택한 지역의 카테고리별 정보를 확인해보세요.";

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className="data">
      <div className="data__title">
        <h2 className="data__title__text">상세 데이터</h2>
        <div className="data__title__tooltip">
          <CustomTooltip content={tooltipMessage} />
        </div>
      </div>
      <Box sx={{ width: "100%" }}>
        <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
          <Tabs
            value={value}
            onChange={handleChange}
            aria-label="basic tabs example"
            className="data__tabs"
          >
            <Tab label="통계" {...a11yProps(0)} />
            <Tab label="부동산" {...a11yProps(1)} />
          </Tabs>
        </Box>
        <TabPanel value={value} index={0}>
          <ChartData statistics={statistics} />
        </TabPanel>
        <TabPanel value={value} index={1}>
          <RealEstateList realEstate={realEstate} />
        </TabPanel>
      </Box>
    </div>
  );
}

export default Data;
