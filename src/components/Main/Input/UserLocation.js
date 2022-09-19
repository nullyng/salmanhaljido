import { FormControl, InputLabel, MenuItem, Select, ThemeProvider } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";

import CustomTooltip from "components/common/CustomTooltip";
import theme from "components/common/theme.js";

function UserLocation() {
  const [sidoList, setSidoList] = useState([]);
  const [sido, setSido] = useState(0);
  const [gugunList, setGugunList] = useState([]);
  const [gugun, setGugun] = useState(0);

  useEffect(() => {
    // 초기 한 번만 시/도 목록을 받아온다.
    axios.get('https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000')
      .then((res) => {
        setSidoList(res.data.regcodes);
      })
  }, [])

  const handleSidoChange = (event) => {
    // 시/도가 바뀔 때 해당하는 시/도의 구/군 목록을 받아온다.
    setSido(event.target.value);

    const sidoCode = sido.slice(0, 2) + "**000000";
    axios.get(`https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=${sidoCode}`)
      .then((res) => {
        setGugunList(res.data.regcodes);
      })
  };

  const handleGugunChange = (event) => {
    setGugun(() => event.target.value);
  };

  return (
    <div className="user-location">
      <div className="user-location__title">
        <h2 className="user-location__title__text">지역 설정</h2>
        <div className="user-location__title__tooltip">
          <CustomTooltip content="현재 살고 있거나 추천 받고 싶은 지역을 선택해 보세요. 해당 지역 위주로 추천 받을 수 있어요." />
        </div>
      </div>
      <div className="user-location__content">
        <ThemeProvider theme={theme}>
          <FormControl className="user-location__content__sido" fullWidth>
            <InputLabel>시/도</InputLabel>
            <Select
              value={sido}
              label="시/도"
              onChange={handleSidoChange}
            >
              {sidoList.map((item, index) => {
                return (
                  <MenuItem key={index} value={item.code}>
                    {item.name}
                  </MenuItem>
                );
              })}
            </Select>
          </FormControl>
          <FormControl className="user-location__content__gugun" fullWidth>
            <InputLabel>구/군</InputLabel>
            <Select
              value={gugun}
              label="구/군"
              onChange={handleGugunChange}
            >
              {gugunList.map((item, index) => {
                return (
                  <MenuItem key={index} value={item.code}>
                    {item.name}
                  </MenuItem>
                );
              })}
            </Select>
          </FormControl>
        </ThemeProvider>
      </div>
    </div>
  )
}

export default UserLocation;