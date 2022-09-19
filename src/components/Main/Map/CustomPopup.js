const { Popup } = require("react-map-gl");

function CustomPopup({ marker, handleClosePopup, handleClickMove }) {
  return (
    <Popup
      offset={30}
      longitude={marker.longitude}
      latitude={marker.latitude}
      onClose={handleClosePopup}
      closeButton={true}
      closeOnClick={false}
    >
      <p>{marker.title}</p>
      <button
        onClick={() => handleClickMove(marker.longitude, marker.latitude)}
      >
        이동
      </button>
    </Popup>
  );
}

export default CustomPopup;
