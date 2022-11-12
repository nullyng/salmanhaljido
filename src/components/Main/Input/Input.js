import { Divider, Drawer, IconButton } from "@mui/material";
import KeyboardDoubleArrowRightIcon from "@mui/icons-material/KeyboardDoubleArrowRight";
import KeyboardDoubleArrowLeftIcon from "@mui/icons-material/KeyboardDoubleArrowLeft";
import { useState } from "react";
import { useSelector } from "react-redux";

import "styles/Main/Input.scss";
import UserLocation from "components/Main/Input/UserLocation";
import UserInfo from "components/Main/Input/UserInfo/UserInfo";
import Price from "components/Main/Input/Price";
import ResetButton from "components/Main/Input/ResetButton";
import SubmitButton from "components/Main/Input/SubmitButton";
import MyCategory from "components/Main/Input/Category/MyCategory";
import Category from "components/Main/Input/Category/Category";

function Input() {
  const [isDrawerOpen, setIsDrawerOpen] = useState(true);

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
          <MyCategory />
          <Divider />
          <Category />
          <Divider />
          <Price />
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
