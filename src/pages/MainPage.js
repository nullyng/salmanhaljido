import { useDispatch, useSelector } from "react-redux";

import Input from "components/Main/Input/Input";
import CustomMap from "components/Main/Map/CustomMap";
import Output from "components/Main/Output/Output";
import { setCurrMap, setMapData, setMarkers } from "modules/map";
import { setCurrRegion, setRealEstate, setStatistics } from "modules/region";

function MainPage() {
  // 지도 관련 state
  const currMap = useSelector((state) => state.map.currMap);
  const mapData = useSelector((state) => state.map.mapData);
  const markers = useSelector((state) => state.map.markers);

  // 입력 관련 state
  const region = useSelector((state) => state.input.region);

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

  // 입력 관련 함수
  const onSetRegion = (region) => dispatch(setRegion(region));
  const onSetUserInfo = (userInfo) => dispatch(setUserInfo(userInfo));
  const onSetCategories = (categories) => dispatch(setCategories(categories));
  const onSetPrice = (price) => dispatch(setPrice(price));

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
        region={region}
        onSetMarkers={onSetMarkers}
        onSetRegion={onSetRegion}
        onSetUserInfo={onSetUserInfo}
        onSetCategories={onSetCategories}
        onSetPrice={onSetPrice}
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
