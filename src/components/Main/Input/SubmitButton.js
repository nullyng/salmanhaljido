import { Button } from "@mui/material";
import { setSurvey } from "modules/survey";
import { useDispatch } from "react-redux";

function SubmitButton({ currMap, markers, onSetMarkers, onSetMapData }) {
  const dispatch = useDispatch();
  const onSetSurvey = (survey) => dispatch(setSurvey(survey));

  const handleClickButton = () => {
    // 데이터 출력 창에 추천 버튼 출력
    onSetSurvey(false);

    markers.map((marker, index) => {
      // 마커 삭제
      marker.remove();

      // 폴리곤 삭제
      currMap.removeLayer(`polygon${index}`);
      currMap.removeLayer(`polygon${index}-outlined`);
      currMap.removeSource(`polygon${index}`);
    });

    onSetMarkers([]);

    onSetMapData([
      {
        sido: "강원도",
        gugun: "춘천시",
        longitude: 127.739868443904,
        latitude: 37.8897967866926,
        SIG_CD: "42110",
      },
      {
        sido: "대구",
        gugun: "서구",
        longitude: 128.549697600138,
        latitude: 35.8750016701213,
        SIG_CD: "27170",
      },
      {
        sido: "대구",
        gugun: "수성구",
        longitude: 128.66127441262,
        latitude: 35.8338488599246,
        SIG_CD: "27260",
      },
      {
        sido: "부산",
        gugun: "해운대구",
        longitude: 129.153589989585,
        latitude: 35.193855123863,
        SIG_CD: "26350",
      },
    ]);
  };

  return (
    <div className="submit-button">
      <Button variant="contained" size="large" onClick={handleClickButton}>
        추천 지역 검색
      </Button>
    </div>
  );
}

export default SubmitButton;
