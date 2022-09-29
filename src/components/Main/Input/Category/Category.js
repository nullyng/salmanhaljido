import { useState } from "react";
import PropTypes from "prop-types";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";

import CustomTooltip from "components/common/CustomTooltip";
import CategoryPanel from "components/Main/Input/Category/CategoryPanel";

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
      {value === index && <Box sx={{ p: 1 }}>{children}</Box>}
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

const categoryList = {
  교통: [
    "대중교통 이용률",
    "대중교통 정류장",
    "전기차 충전소",
    "공용, 민영주차장",
  ],
  재난: ["민방위 대피소"],
  안전: ["교통사고", "화재", "범죄", "어린이 보호구역", "여성안심"],
  의료: ["의료기관", "약국"],
  반려동물: ["동물병원", "동물미용"],
  교육: ["유치원", "초, 중, 고등학교", "학원 및 교습소"],
  문화: ["미술관, 박물관", "영화관", "도서관", "공연장", "스포츠시설"],
  생활: ["음식점", "공원", "마트, 시장", "장애인 편의시설"],
};

function Category() {
  const [value, setValue] = useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  const handleChange2 = (event, newValue) => {
    setValue(newValue + 4);
  };

  const tooltipMessage =
    "8개의 카테고리에서 중요하게 생각하는 세부 카테고리를 고르고, 각 카테고리의 중요도를 설정해 보세요. " +
    "상, 중, 하의 세 단계로 나눠져 있어서 더욱 정밀한 지역 추천을 받을 수 있어요.";

  return (
    <div className="category">
      <div className="category__title">
        <h2 className="category__title__text">카테고리 설정</h2>
        <div className="category__title__tooltip">
          <CustomTooltip content={tooltipMessage} />
        </div>
      </div>
      <Box sx={{ width: "100%" }}>
        <Box
          sx={{ borderBottom: 1, borderColor: "divider" }}
          className="category__box"
        >
          <Tabs value={value} onChange={handleChange}>
            <Tab label="교통" {...a11yProps(0)} />
            <Tab label="재난" {...a11yProps(1)} />
            <Tab label="안전" {...a11yProps(2)} />
            <Tab label="의료" {...a11yProps(3)} />
          </Tabs>
          <Tabs value={value - 4} onChange={handleChange2}>
            <Tab label="반려동물" {...a11yProps(4)} />
            <Tab label="문화" {...a11yProps(5)} />
            <Tab label="교육" {...a11yProps(6)} />
            <Tab label="생활" {...a11yProps(7)} />
          </Tabs>
        </Box>
        {
          <div>
            {" "}
            {Object.keys(categoryList).map((category, index) => (
              <TabPanel key={index} value={value} index={index}>
                <CategoryPanel categoryList={categoryList[category]} />
              </TabPanel>
            ))}
          </div>
        }
      </Box>
    </div>
  );
}

export default Category;
