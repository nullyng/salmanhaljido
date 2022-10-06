import { Button, Rating, ThemeProvider } from "@mui/material";
import StarIcon from "@mui/icons-material/Star";
import { useDispatch } from "react-redux";

import "styles/Main/Output.scss";
import theme from "components/common/theme";
import { setCurrRegion, setRealEstate, setStatistics } from "modules/region";

function RcmdItem({ index, data }) {
  const dispatch = useDispatch();
  const onSetCurrRegion = (currRegion) => dispatch(setCurrRegion(currRegion));
  const onSetStatistics = (statistics) => dispatch(setStatistics(statistics));
  const onSetRealEstate = (realEstate) => dispatch(setRealEstate(realEstate));

  const handleClick = () => {
    // 현재 선택한 지역, 해당 지역의 통계 및 부동산 정보 저장
    onSetCurrRegion(data);
    onSetStatistics(data.stat);
    onSetRealEstate(data.re);
  };

  return (
    <ThemeProvider theme={theme}>
      <Button className={`rcmd-item ${data.code}`} onClick={handleClick}>
        <div className="rcmd-item__content">
          <div className="rcmd-item__content__data">
            <span className="rcmd-item__content__data__ranking">
              {index + 1}
            </span>
            <span className="rcmd-item__content__data__region">
              {data.addr}
            </span>
          </div>
          <div className="rcmd-item__content__rating">
            <Rating
              name="read-only"
              defaultValue={5 - 0.3 * index}
              precision={0.1}
              icon={<StarIcon />}
              readOnly
            />
          </div>
        </div>
      </Button>
    </ThemeProvider>
  );
}

export default RcmdItem;
