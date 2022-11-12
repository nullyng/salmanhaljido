import { useDispatch, useSelector } from "react-redux";

import Input from "components/Main/Input/Input";
import CustomMap from "components/Main/Map/CustomMap";
import Output from "components/Main/Output/Output";
import { setCurrMap, setMapData, setMarkers } from "modules/map";
import Loading from "components/common/Loading";

function MainPage() {
  // 지도 관련 state
  const currMap = useSelector((state) => state.map.currMap);
  const mapData = useSelector((state) => state.map.mapData);
  const markers = useSelector((state) => state.map.markers);

  const dispatch = useDispatch();
  // 지도 관련 함수
  const onSetCurrMap = (currMap) => dispatch(setCurrMap(currMap));
  const onSetMapData = (mapData) => dispatch(setMapData(mapData));
  const onSetMarkers = (markers) => dispatch(setMarkers(markers));

  const loading = useSelector((state) => state.loading.loading);

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
      <Output />
      <CustomMap
        onSetCurrMap={onSetCurrMap}
        mapData={mapData}
        onSetMarkers={onSetMarkers}
      />
    </div>
  );
}

export default MainPage;
