import { Drawer, IconButton } from "@mui/material";
import KeyboardDoubleArrowRightIcon from '@mui/icons-material/KeyboardDoubleArrowRight';
import KeyboardDoubleArrowLeftIcon from '@mui/icons-material/KeyboardDoubleArrowLeft';
import { useState } from "react";

import "styles/Main/Output.scss";

function Output() {
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const handleDrawerOpen = () => {
    setIsDrawerOpen(true);
  }

  const handleDrawerClose = () => {
    setIsDrawerOpen(false);
  }

  return (
    <div className="right-drawer">
      <div className="right-drawer__button">
        <IconButton onClick={handleDrawerOpen}>
          <KeyboardDoubleArrowLeftIcon />
        </IconButton>
      </div>
      <Drawer
        className="right-drawer__inner"
        variant="persistent"
        anchor="right"
        open={isDrawerOpen}>
        <div className="right-drawer__inner__button" >
          <IconButton onClick={handleDrawerClose}>
            <KeyboardDoubleArrowRightIcon />
          </IconButton>
        </div>
      </Drawer>
    </div>
  )
}

export default Output;