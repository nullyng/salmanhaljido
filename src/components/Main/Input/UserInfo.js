import { Checkbox, FormControlLabel, ThemeProvider } from "@mui/material";
import { useState } from "react";

import CustomTooltip from "components/common/CustomTooltip";
import theme from "components/common/theme";
import CategoryRcmd from "./CategoryRcmd";

function UserInfo() {
  const [hasCar, setHasCar] = useState(false);
  const [hasPets, setHasPets] = useState(false);
  const [hasChildren, setHasChildren] = useState(false);

  const tooltipMessage =
    "나에게 해당하는 환경을 선택해보세요. 나와 비슷한 환경의 사람들이 어떤 카테고리를 선택했는지 알 수 있어요.";

  const handleHasCar = (event) => {
    setHasCar(event.target.checked);
  };

  const handleHasPets = (event) => {
    setHasPets(event.target.checked);
  };

  const handleHasChildren = (event) => {
    setHasChildren(event.target.checked);
  };

  return (
    <div className="user-info">
      <div className="user-info__title">
        <h2 className="user-info__title__text">사용자 정보</h2>
        <div className="user-info__title__tooltip">
          <CustomTooltip content={tooltipMessage} />
        </div>
      </div>
      <ThemeProvider theme={theme}>
        <div className="user-info__content">
          <div className="user-info__content__form">
            <FormControlLabel
              control={<Checkbox checked={hasCar} onChange={handleHasCar} />}
              label="자가용"
            />
            <FormControlLabel
              control={<Checkbox checked={hasPets} onChange={handleHasPets} />}
              label="반려동물"
            />
            <FormControlLabel
              control={
                <Checkbox checked={hasChildren} onChange={handleHasChildren} />
              }
              label="자녀"
            />
          </div>
          {hasCar || hasPets || hasChildren ? (
            <CategoryRcmd open={true} />
          ) : (
            <CategoryRcmd open={false} />
          )}
        </div>
      </ThemeProvider>
    </div>
  );
}

export default UserInfo;
