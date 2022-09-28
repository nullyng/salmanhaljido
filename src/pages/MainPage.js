import Input from "components/Main/Input/Input";
import CustomMap from "components/Main/Map/CustomMap";
import Output from "components/Main/Output/Output";
import { setCurrMap, setMapData, setMarkers } from "modules/map";
import { useDispatch, useSelector } from "react-redux";

function MainPage() {
  const currMap = useSelector((state) => state.map.currMap);
  const mapData = useSelector((state) => state.map.mapData);
  const markers = useSelector((state) => state.map.markers);

  const dispatch = useDispatch();
  const onSetCurrMap = (currMap) => dispatch(setCurrMap(currMap));
  const onSetMapData = (mapData) => dispatch(setMapData(mapData));
  const onSetMarkers = (markers) => dispatch(setMarkers(markers));

  return (
    <div>
      <Input currMap={currMap} onSetMapData={onSetMapData} markers={markers} onSetMarkers={onSetMarkers} />
      <Output />
      <CustomMap onSetCurrMap={onSetCurrMap} mapData={mapData} onSetMarkers={onSetMarkers} />
    </div>
  );
}

export default MainPage;
