import { Drawer, IconButton } from "@mui/material";
import KeyboardDoubleArrowRightIcon from "@mui/icons-material/KeyboardDoubleArrowRight";
import KeyboardDoubleArrowLeftIcon from "@mui/icons-material/KeyboardDoubleArrowLeft";
import { useState } from "react";
import { ThemeProvider, createTheme } from "@mui/material/styles";

import "styles/Main/Output.scss";
import RcmdList from "components/Main/Output/Rcmd/RcmdList";
import Data from "components/Main/Output/Data/Data";

function Output({
  rcmdData,
  currRegion,
  statistics,
  realEstate,
  onSetCurrRegion,
  onSetStatistics,
  onSetRealEstate,
}) {
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const handleDrawerOpen = () => {
    setIsDrawerOpen(true);
  };

  const handleDrawerClose = () => {
    setIsDrawerOpen(false);
  };

  // 스타일
  const theme = createTheme({
    palette: {
      primary: {
        main: "#4D88C6",
      },
      secondary: {
        main: "#0F3460",
      },
    },
  });

  return (
    <ThemeProvider theme={theme}>
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
          open={isDrawerOpen}
        >
          <div className="right-drawer__inner__button">
            <IconButton onClick={handleDrawerClose}>
              <KeyboardDoubleArrowRightIcon />
            </IconButton>
          </div>
          <RcmdList
            rcmdData={rcmdData}
            currRegion={currRegion}
            onSetCurrRegion={onSetCurrRegion}
            onSetStatistics={onSetStatistics}
            onSetRealEstate={onSetRealEstate}
          />
          <Data
            currRegion={currRegion}
            statistics={statistics}
            realEstate={realEstate}
          />
        </Drawer>
      </div>
    </ThemeProvider>
  );
}

export default Output;
