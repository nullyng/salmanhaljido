import CustomTooltip from "components/common/CustomTooltip";

import * as React from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import CategoryPanel from "./CategoryPanel";
import { useSelector } from "react-redux";

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
          <Box sx={{ p: 1 }}>
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
    'aria-controls': `simple-tabpanel-${index}`,
    };
}

    
function Category() {
    const [value, setValue] = React.useState(0);

    const handleChange = (event, newValue) => {
    setValue(newValue);
    };

    const tooltipMessage =
    "8개의 카테고리에서 필요한 세부카테고리를 골라보세요. 중요도는 상, 중, 하로 나뉘어져 있고 필요없을 시 선택안함 또는 내 카테고리에서 X를 클릭하면 삭제할 수 있어요.";
      
    const { CategoryList } = useSelector((state) => state.CategorySet);
    return(
        <div className="category">
            <div className="category__title">
                <h2 className="category__title__text">카테고리 설정</h2>
                <div className="user-location__title__tooltip">
                    <CustomTooltip content={tooltipMessage} />
                </div>
            </div>
            <Box sx={{ width: '100%' }}>
            <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                <Tabs value={value} onChange={handleChange} aria-label="basic tabs example" className="category__tabs">
                <Tab label="교통" {...a11yProps(0)}/>
                <Tab label="재난" {...a11yProps(1)}/>
                <Tab label="안전" {...a11yProps(2)}/>
                <Tab label="의료" {...a11yProps(3)}/>
                <Tab label="반려동물" {...a11yProps(4)}/>
                <Tab label="문화" {...a11yProps(5)}/>
                <Tab label="교육" {...a11yProps(6)}/>
                <Tab label="생활" {...a11yProps(7)}/>
                </Tabs>
            </Box>
            <TabPanel value={value} index={0}>
                <CategoryPanel arr={CategoryList.교통}/>
            </TabPanel>
            <TabPanel value={value} index={1}>
                <CategoryPanel arr={CategoryList.재난}/>
            </TabPanel>
            <TabPanel value={value} index={2}>
                <CategoryPanel arr={CategoryList.안전}/>
            </TabPanel>
            <TabPanel value={value} index={3}>
                <CategoryPanel arr={CategoryList.의료}/>
            </TabPanel>
            <TabPanel value={value} index={4}>
                <CategoryPanel arr={CategoryList.반려동물}/>
            </TabPanel>
            <TabPanel value={value} index={5}>
                <CategoryPanel arr={CategoryList.교육}/>
            </TabPanel>
            <TabPanel value={value} index={6}>
                <CategoryPanel arr={CategoryList.문화}/>
            </TabPanel>
            <TabPanel value={value} index={7}>
                <CategoryPanel arr={CategoryList.생활}/>
            </TabPanel>
            </Box>
        </div>
    )
 }

export default Category;