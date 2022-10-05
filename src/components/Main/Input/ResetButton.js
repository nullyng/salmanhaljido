import { Button } from "@mui/material";
import RefreshIcon from "@mui/icons-material/Refresh";
import { useState } from "react";
import { useDispatch } from "react-redux";

import CustomModal from "components/common/CustomModal";
import { setPrice, setRegion, setUserInfo } from "modules/input";
import { initCategory } from "modules/category";

import { setCurrRegion, setRcmdData } from "modules/region";
import { setSurvey } from "modules/survey";


function ResetButton() {
  const [open, setOpen] = useState(false);

  const dispatch = useDispatch();
  const onSetRegion = (region) => dispatch(setRegion(region));
  const onSetUserInfo = (userInfo) => dispatch(setUserInfo(userInfo));
  const onSetPrice = (price) => dispatch(setPrice(price));
  const onInitCategory = () => dispatch(initCategory());

  const onSetRcmdData = (rcmdData) => dispatch(setRcmdData(rcmdData));
  const onSetCurrRegion = (currRegion) => dispatch(setCurrRegion(currRegion));
  const onSetSurvey = (survey) => dispatch(setSurvey(survey));

  const handleClickButton = () => {
    onSetRegion("");
    onSetUserInfo({
      isMarried: false,
      hasCar: false,
      hasPets: false,
      hasChildren: false,
    });
    onSetPrice({ jeonse: [0, 19], maemae: [0, 24] });
    onInitCategory();

    onSetRcmdData([]);
    onSetCurrRegion({});
    onSetSurvey(true);
  };

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
        handleClickButton={handleClickButton}
      />
    </div>
  );
}

export default ResetButton;
