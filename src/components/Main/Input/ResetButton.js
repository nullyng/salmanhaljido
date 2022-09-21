import { Button } from "@mui/material";
import RefreshIcon from "@mui/icons-material/Refresh";

function ResetButton() {
  return (
    <div className="reset-button">
      <Button
        variant="outlined"
        size="large"
        startIcon={<RefreshIcon />}
        onClick={() => console.log("refresh")}
      >
        선택 초기화
      </Button>
    </div>
  );
}

export default ResetButton;
