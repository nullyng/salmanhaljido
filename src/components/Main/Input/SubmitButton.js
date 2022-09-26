import { Button } from "@mui/material";

function SubmitButton({ onSetRcmdData }) {
  const handleClickButton = () => {
    onSetRcmdData([
      { title: "춘천시", longitude: 127.739868443904, latitude: 37.8897967866926 },
      { title: "수성구", longitude: 128.66127441262, latitude: 35.8338488599246 },
      { title: "해운대구", longitude: 129.153589989585, latitude: 35.193855123863 },
    ]);
  }

  return (
    <div className="submit-button">
      <Button
        variant="contained"
        size="large"
        onClick={handleClickButton}
      >
        추천 지역 검색
      </Button>
    </div>
  );
}

export default SubmitButton;
