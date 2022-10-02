import {
  Checkbox,
  createTheme,
  FormControlLabel,
  ThemeProvider,
} from "@mui/material";

import CustomTooltip from "components/common/CustomTooltip";
import CategoryRcmdButton from "components/Main/Input/UserInfo/CategoryRcmdButton";
import { useDispatch, useSelector } from "react-redux";
import { setUserInfo } from "modules/input";

const theme = createTheme({
  palette: {
    primary: {
      main: "#4d89c6",
    },
  },
});

function UserInfo() {
  const tooltipMessage =
    "나에게 해당하는 환경을 선택해 보세요. 나와 비슷한 환경의 사람들이 어떤 카테고리를 선택했는지 추천받을 수 있어요.";

  const dispatch = useDispatch();
  const userInfo = useSelector((state) => state.input.userInfo);
  const onSetUserInfo = (userInfo) => dispatch(setUserInfo(userInfo));

  const handleIsMarried = () => {
    const obj = { ...userInfo, isMarried: !userInfo["isMarried"] };
    onSetUserInfo(obj);
  };

  const handleHasCar = () => {
    const obj = { ...userInfo, hasCar: !userInfo["hasCar"] };
    onSetUserInfo(obj);
  };

  const handleHasPets = () => {
    const obj = { ...userInfo, hasPets: !userInfo["hasPets"] };
    onSetUserInfo(obj);
  };

  const handleHasChildren = () => {
    const obj = { ...userInfo, hasChildren: !userInfo["hasChildren"] };
    onSetUserInfo(obj);
  };

  return (
    <div className="user-info">
      <div className="user-info__title">
        <h2 className="user-info__title__text">사용자 정보</h2>
        <div className="user-info__title__tooltip">
          <CustomTooltip content={tooltipMessage} />
        </div>
      </div>
      <div className="user-info__content">
        <div className="user-info__content__form">
          <ThemeProvider theme={theme}>
            <FormControlLabel
              control={
                <Checkbox
                  checked={userInfo["isMarried"]}
                  onChange={handleIsMarried}
                />
              }
              label="기혼"
            />
            <FormControlLabel
              control={
                <Checkbox
                  checked={userInfo["hasCar"]}
                  onChange={handleHasCar}
                />
              }
              label="자가용"
            />
            <FormControlLabel
              control={
                <Checkbox
                  checked={userInfo["hasPets"]}
                  onChange={handleHasPets}
                />
              }
              label="반려동물"
            />
            <FormControlLabel
              control={
                <Checkbox
                  checked={userInfo["hasChildren"]}
                  onChange={handleHasChildren}
                />
              }
              label="자녀"
            />
          </ThemeProvider>
        </div>
        {userInfo["isMarried"] ||
        userInfo["hasCar"] ||
        userInfo["hasPets"] ||
        userInfo["hasChildren"] ? (
          <CategoryRcmdButton
            isMarried={userInfo["isMarried"]}
            hasCar={userInfo["hasCar"]}
            hasPets={userInfo["hasPets"]}
            hasChildren={userInfo["hasChildren"]}
            open={true}
          />
        ) : (
          <CategoryRcmdButton open={false} />
        )}
      </div>
    </div>
  );
}

export default UserInfo;
