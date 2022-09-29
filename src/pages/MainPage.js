import Input from "components/Main/Input/Input";
import CustomMap from "components/Main/Map/CustomMap";
import Output from "components/Main/Output/Output";
import { setCategories, setPrice, setRegion, setUserInfo } from "modules/input";
import { setCurrMap, setMapData, setMarkers } from "modules/map";
import { useDispatch, useSelector } from "react-redux";

function MainPage() {
  const currMap = useSelector((state) => state.map.currMap);
  const mapData = useSelector((state) => state.map.mapData);
  const markers = useSelector((state) => state.map.markers);

  const region = useSelector((state) => state.input.region);

  const dispatch = useDispatch();
  const onSetCurrMap = (currMap) => dispatch(setCurrMap(currMap));
  const onSetMapData = (mapData) => dispatch(setMapData(mapData));
  const onSetMarkers = (markers) => dispatch(setMarkers(markers));

  const onSetRegion = (region) => dispatch(setRegion(region));
  const onSetUserInfo = (userInfo) => dispatch(setUserInfo(userInfo));
  const onSetCategories = (categories) => dispatch(setCategories(categories));
  const onSetPrice = (price) => dispatch(setPrice(price));

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
