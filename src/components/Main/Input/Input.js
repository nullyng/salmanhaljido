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
      <IconButton className="left-drawer__button" onClick={handleDrawerOpen}>
        <KeyboardDoubleArrowRightIcon />
      </IconButton>
      <Drawer
        variant="persistent"
        anchor="left"
        open={isDrawerOpen}>
        <IconButton onClick={handleDrawerClose}>
          <KeyboardDoubleArrowLeftIcon />
        </IconButton>
      </Drawer>
    </div>
  )
}

export default Input;