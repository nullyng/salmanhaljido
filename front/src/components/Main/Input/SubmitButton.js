import { Button, Snackbar } from "@mui/material";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import { setMarkers } from "modules/map";
import { setSurvey } from "modules/survey";
import { getRegionRcmd } from "api/rcmd";
import { setLoading } from "modules/loading";
import { setCurrRegion, setRcmdData } from "modules/region";
import { jeonseList, maemaeList } from "./valueList";

function SubmitButton() {
  const [snackbarOpen, setSnackbarOpen] = useState(false);

  const currMap = useSelector((state) => state.map.currMap);
  const markers = useSelector((state) => state.map.markers);

  const region = useSelector((state) => state.input.region);
  const myCategoryList = useSelector((state) => state.category.myCategoryList);
  const price = useSelector((state) => state.input.price);

  const dispatch = useDispatch();
  const onSetSurvey = (survey) => dispatch(setSurvey(survey));
  const onSetMarkers = (markers) => dispatch(setMarkers(markers));

  const onSetLoading = (loading) => dispatch(setLoading(loading));

  const onSetRcmdData = (rcmdData) => dispatch(setRcmdData(rcmdData));
  const onSetCurrRegion = (currRegion) => dispatch(setCurrRegion(currRegion));

  const handleClickButton = () => {
    // 이전에 선택한 현재 지역 정보 삭제
    onSetCurrRegion({});

    // api 통신
    let apiData = {
      jeonseLow: jeonseList[price["jeonse"][0]].value,
      jeonseHigh: jeonseList[price["jeonse"][1]].value,
      tradingLow: maemaeList[price["maemae"][0]].value,
      tradingHigh: maemaeList[price["maemae"][1]].value,
      ...myCategoryList
    };
    if (region.length === 0) {
      apiData = {
        ...apiData
      };
    } else {
      apiData = {
        code: region,
        ...apiData,
      };
    }

    onSetLoading(true);

    getRegionRcmd(apiData, (res) => {
      // 데이터는 최대 8개까지 출력
      onSetRcmdData(
        res.data.regions.length > 8
          ? res.data.regions.slice(0, 8)
          : res.data.regions
      );
      onSetLoading(false);


      // 만약 선택한 지역이 있다면 그 지역으로 확대
      if (region.length > 0) {
        currMap.flyTo({
          center: [res.data.regions[0].lng, res.data.regions[0].lat],
          duration: 600,
          essential: true,
          zoom: 10,
        });
      }

      // 데이터 출력 창에 추천 버튼 출력
      onSetSurvey(false);
      // 스낵바 출력
      setSnackbarOpen(true);

      markers.map((marker, index) => {
        // 기존에 있던 마커 삭제
        marker.remove();

        // 기존에 있던 폴리곤 삭제
        currMap.removeLayer(`polygon${index}`);
        currMap.removeLayer(`polygon${index}-outlined`);
        currMap.removeSource(`polygon${index}`);
      });

      onSetMarkers([]);
    });
  };

  return (
    <div className="submit-button">
      <Button variant="contained" size="large" onClick={handleClickButton}>
        추천 지역 검색
      </Button>
      <Snackbar
        open={snackbarOpen}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
        onClose={() => setSnackbarOpen(false)}
        message="마커를 클릭해서 추천 지역을 둘러보세요!"
        autoHideDuration={2500}
      />
    </div>
  );
}

export default SubmitButton;
