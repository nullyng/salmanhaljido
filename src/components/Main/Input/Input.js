import { Drawer, IconButton } from "@mui/material";
import KeyboardDoubleArrowRightIcon from '@mui/icons-material/KeyboardDoubleArrowRight';
import KeyboardDoubleArrowLeftIcon from '@mui/icons-material/KeyboardDoubleArrowLeft';
import { useState } from "react";

import "styles/Main/Input.scss";

function Input() {
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const handleDrawerOpen = () => {
    setIsDrawerOpen(true);
  }

  const handleDrawerClose = () => {
    setIsDrawerOpen(false);
  }

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
        open={isDrawerOpen}>
        <div className="left-drawer__inner__button" >
          <IconButton onClick={handleDrawerClose}>
            <KeyboardDoubleArrowLeftIcon />
          </IconButton>
        </div>
      </Drawer>
    </div>
  )
}

export default Input;