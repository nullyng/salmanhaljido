import {
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  ThemeProvider,
} from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import CustomTooltip from "components/common/CustomTooltip";
import theme from "components/common/theme.js";
import { setRegion } from "modules/input";

function UserLocation() {
  const [sidoList, setSidoList] = useState([]);

  const tooltipMessage =
    "현재 살고 있거나 추천 받고 싶은 지역을 선택해 보세요. 해당 지역 위주로 추천 받을 수 있어요.";

  const dispatch = useDispatch();
  const region = useSelector((state) => state.input.region);
  const onSetRegion = (region) => dispatch(setRegion(region));

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
    onSetRegion(event.target.value);
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
          <FormControl
            className="user-location__content__sido"
            size="small"
            fullWidth
          >
            <InputLabel>시/도</InputLabel>
            <Select value={region} label="시/도" onChange={handleSidoChange}>
              {sidoList.map((item, index) => {
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
  );
}

export default UserLocation;
