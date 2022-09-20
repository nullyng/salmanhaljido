import {
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  ThemeProvider,
} from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";

import CustomTooltip from "components/common/CustomTooltip";
import theme from "components/common/theme.js";

function UserLocation() {
  const [sidoList, setSidoList] = useState([]);
  const [sido, setSido] = useState("");
  const [gugunList, setGugunList] = useState([]);
  const [gugun, setGugun] = useState("");

  const tooltipMessage =
    "현재 살고 있거나 추천 받고 싶은 지역을 선택해 보세요. 해당 지역 위주로 추천 받을 수 있어요.";

  useEffect(() => {
    // 초기 한 번만 시/도 목록을 받아온다.
    axios
      .get(
        "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000"
      )
      .then((res) => {
        setSidoList(() => res.data.regcodes);
      });
  }, []);

  const handleSidoChange = (event) => {
    // 시/도가 바뀔 때 해당하는 시/도의 구/군 목록을 받아온다.
    setSido(event.target.value);

    // 시/도 코드 2글자 + **000000
    const sidoCode = event.target.value.substring(0, 2) + "**000000";
    axios
      .get(
        `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=${sidoCode}`
      )
      .then((res) => {
        setGugunList(res.data.regcodes);
      });
  };

  const handleGugunChange = (event) => {
    setGugun(() => event.target.value);
  };

  return (
    <div className="user-location">
      <div className="user-location__title">
        <h2 className="user-location__title__text">지역 설정</h2>
        <div className="user-location__title__tooltip">
          <CustomTooltip content={tooltipMessage} />
        </div>
      </div>
      <div className="user-location__content">
        <ThemeProvider theme={theme}>
          <FormControl className="user-location__content__sido" fullWidth>
            <InputLabel>시/도</InputLabel>
            <Select value={sido} label="시/도" onChange={handleSidoChange}>
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
            <Select value={gugun} label="구/군" onChange={handleGugunChange}>
              {gugunList.map((item, index) => {
                // 0번째 인덱스는 항상 시/도 그 자체이므로 건너뛴다.
                if (index == 0) return;
                // 받아온 구/군 이름을 ' '로 분할하여 뒤쪽의 구/군만 사용한다.
                // 받아온 구/군 이름 예시 : 서울특별시 종로구
                const arr = item.name.split(" ", 2);
                return (
                  <MenuItem key={index} value={item.code}>
                    {arr[1]}
                  </MenuItem>
                );
              })}
            </Select>
          </FormControl>
        </ThemeProvider>
      </div>
    </div>
  );
}

export default UserLocation;
