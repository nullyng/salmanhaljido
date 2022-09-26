import { Button } from "@mui/material";

function SubmitButton() {
  return (
    <div className="submit-button">
      <Button
        variant="contained"
        size="large"
        onClick={() => console.log("search")}
      >
        추천 지역 검색
      </Button>
    </div>
  );
}

export default SubmitButton;
