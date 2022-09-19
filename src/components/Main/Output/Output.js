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
      <IconButton className="right-drawer__button" onClick={handleDrawerOpen}>
        <KeyboardDoubleArrowLeftIcon />
      </IconButton>
      <Drawer
        variant="persistent"
        anchor="right"
        open={isDrawerOpen}>
        <IconButton onClick={handleDrawerClose}>
          <KeyboardDoubleArrowRightIcon />
        </IconButton>
      </Drawer>
    </div>
  )
}

export default Output;