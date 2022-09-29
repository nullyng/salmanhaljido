import { useDispatch, useSelector } from "react-redux";

import Input from "components/Main/Input/Input";
import CustomMap from "components/Main/Map/CustomMap";
import Output from "components/Main/Output/Output";
import { setCurrMap, setMapData, setMarkers } from "modules/map";
import { setCategories, setPrice, setRegion, setUserInfo } from "modules/input";
import { setCurrRegion, setRealEstate, setStatistics } from "modules/region";

function MainPage() {
  // 지도 관련 state
  const currMap = useSelector((state) => state.map.currMap);
  const mapData = useSelector((state) => state.map.mapData);
  const markers = useSelector((state) => state.map.markers);

  // 출력 관련 state
  const rcmdData = useSelector((state) => state.region.rcmdData);
  const currRegion = useSelector((state) => state.region.currRegion);
  const statistics = useSelector((state) => state.region.statistics);
  const realEstate = useSelector((state) => state.region.realEstate);

  const dispatch = useDispatch();
  // 지도 관련 함수
  const onSetCurrMap = (currMap) => dispatch(setCurrMap(currMap));
  const onSetMapData = (mapData) => dispatch(setMapData(mapData));
  const onSetMarkers = (markers) => dispatch(setMarkers(markers));

  // 출력 관련 함수
  const onSetCurrRegion = (currRegion) => dispatch(setCurrRegion(currRegion));
  const onSetStatistics = (statistics) => dispatch(setStatistics(statistics));
  const onSetRealEstate = (realEstate) => dispatch(setRealEstate(realEstate));

  return (
    <div>
      <Input
        currMap={currMap}
        onSetMapData={onSetMapData}
        markers={markers}
        onSetMarkers={onSetMarkers}
      />

      <Output
        rcmdData={rcmdData}
        currRegion={currRegion}
        statistics={statistics}
        realEstate={realEstate}
        onSetCurrRegion={onSetCurrRegion}
        onSetStatistics={onSetStatistics}
        onSetRealEstate={onSetRealEstate}
      />
      <CustomMap
        onSetCurrMap={onSetCurrMap}
        mapData={mapData}
        onSetMarkers={onSetMarkers}
      />
    </div>
  );
}

export default MainPage;
