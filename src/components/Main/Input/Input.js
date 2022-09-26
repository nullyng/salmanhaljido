import { Divider, Drawer, IconButton } from "@mui/material";
import KeyboardDoubleArrowRightIcon from "@mui/icons-material/KeyboardDoubleArrowRight";
import KeyboardDoubleArrowLeftIcon from "@mui/icons-material/KeyboardDoubleArrowLeft";
import { useState } from "react";

import "styles/Main/Input.scss";
import UserLocation from "components/Main/Input/UserLocation";
import UserInfo from "components/Main/Input/UserInfo";
import Price from "components/Main/Input/Price";
import ResetButton from "./ResetButton";
import SubmitButton from "./SubmitButton";
import MyCategory from "./MyCategory";

function Input() {
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const handleDrawerOpen = () => {
    setIsDrawerOpen(true);
  };

  const handleDrawerClose = () => {
    setIsDrawerOpen(false);
  };

  return (
    <div className="left-drawer">
      <div className="left-drawer__button">
        <IconButton onClick={handleDrawerOpen}>
          <KeyboardDoubleArrowRightIcon />
        </IconButton>
      </div>
      <Drawer
        className="left-drawer__inner"
        variant="persistent"
        anchor="left"
        open={isDrawerOpen}
      >
        <div className="left-drawer__inner__button">
          <IconButton onClick={handleDrawerClose}>
            <KeyboardDoubleArrowLeftIcon />
          </IconButton>
        </div>
        <div className="left-drawer__inner__content">
          <UserLocation />
          <Divider />
          <UserInfo />
          <Divider />
          <Price />
          <Divider />
          <MyCategory />
        </div>
        <div className="left-drawer__inner__bottom">
          <ResetButton />
          <SubmitButton />
        </div>
      </Drawer>
    </div>
  );
}

export default Input;
