import { Marker } from "react-map-gl";

function CustomMarker({ index, marker, handleOpenPopup, handleClickMove }) {
  return (
    <Marker
      longitude={marker.longitude}
      latitude={marker.latitude}
      onClick={() => handleClickMove(marker.longitude, marker.latitude)}
    >
      {/* <div className="marker" onClick={() => handleOpenPopup(index)}></div> */}
    </Marker>
  );
}

export default CustomMarker;
