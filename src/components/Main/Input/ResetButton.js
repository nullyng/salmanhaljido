import { Button } from "@mui/material";
import RefreshIcon from "@mui/icons-material/Refresh";
import { useState } from "react";
import CustomModal from "components/common/CustomModal";

function ResetButton() {
  const [open, setOpen] = useState(false);

  return (
    <div className="reset-button">
      <Button
        variant="outlined"
        size="large"
        startIcon={<RefreshIcon />}
        onClick={() => setOpen(true)}
      >
        선택 초기화
      </Button>
      <CustomModal
        open={open}
        setOpen={setOpen}
        title="잠깐!"
        content="선택한 모든 정보가 초기화돼요.\n그래도 계속하실 건가요?"
        cancelMessage="아니오"
        okMessage="계속 할래요"
      />
    </div>
  );
}

export default ResetButton;