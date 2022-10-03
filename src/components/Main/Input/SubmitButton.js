import { Button, ListItem, Snackbar } from "@mui/material";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import { setMapData, setMarkers } from "modules/map";
import { setSurvey } from "modules/survey";
import { getRegionRcmd } from "api/rcmd";
import { setLoading } from "modules/loading";
import { setRcmdData } from "modules/region";

function SubmitButton() {
  const [snackbarOpen, setSnackbarOpen] = useState(false);

  const currMap = useSelector((state) => state.map.currMap);
  const markers = useSelector((state) => state.map.markers);

  const region = useSelector((state) => state.input.region);
  const myCategoryList = useSelector((state) => state.category.myCategoryList);

  const dispatch = useDispatch();
  const onSetSurvey = (survey) => dispatch(setSurvey(survey));
  const onSetMarkers = (markers) => dispatch(setMarkers(markers));

  const onSetLoading = (loading) => dispatch(setLoading(loading));

  const onSetRcmdData = (rcmdData) => dispatch(setRcmdData(rcmdData));

  const handleClickButton = () => {
    // api 통신
    let apiData = {};
    if (region.length === 0) {
      apiData = {
        ...myCategoryList,
      };
    } else {
      apiData = {
        code: region,
        ...myCategoryList,
      };
    }

    onSetLoading(true);

    getRegionRcmd(apiData, (res) => {
      // 데이터는 최대 8개까지 출력
      onSetRcmdData(
        res.data.regions.length > 10
          ? res.data.regions.slice(0, 10)
          : res.data.regions
      );
      onSetLoading(false);

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
