import { Marker } from "react-map-gl";

function CustomMarker({ marker, handleClickMove }) {
  return (
    <Marker
      longitude={marker.longitude}
      latitude={marker.latitude}
      onClick={() => handleClickMove(marker.longitude, marker.latitude)}
    ></Marker>
  );
}

export default CustomMarker;
