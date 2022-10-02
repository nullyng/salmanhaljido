import { useState } from "react";
import { useSelector } from "react-redux";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import {
  Divider,
  Drawer,
  IconButton,
  Tooltip,
  tooltipClasses,
} from "@mui/material";
import styled from "@emotion/styled";
import KeyboardDoubleArrowRightIcon from "@mui/icons-material/KeyboardDoubleArrowRight";
import KeyboardDoubleArrowLeftIcon from "@mui/icons-material/KeyboardDoubleArrowLeft";
import ThumbUpAltIcon from "@mui/icons-material/ThumbUpAlt";
import "animate.css";

import "styles/Main/Output.scss";
import RcmdList from "components/Main/Output/Rcmd/RcmdList";
import Data from "components/Main/Output/Data/Data";
import RcmdModal from "components/Main/Output/RcmdModal";

const MyTooltip = styled(({ className, ...props }) => (
  <Tooltip {...props} arrow classes={{ popper: className }} />
))(({ theme }) => ({
  [`& .${tooltipClasses.arrow}`]: {
    color: "#16213E",
  },
  [`& .${tooltipClasses.tooltip}`]: {
    backgroundColor: "#16213E",
    fontSize: "1.5rem",
    margin: "1rem",
    padding: "0.5rem",
    fontFamily: "EsamanruLight",
  },
}));

function Output() {
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  const [rcmdOpen, setRcmdOpen] = useState(false);

  const survey = useSelector((state) => state.survey.survey);

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
            {survey === false && (
              <MyTooltip title="추천하기" arrow>
                <IconButton
                  className="right-drawer__inner__button--rcmd"
                  onClick={() => setRcmdOpen(true)}
                >
                  <ThumbUpAltIcon className="animate__animated animate__heartBeat animate__infinite" />
                </IconButton>
              </MyTooltip>
            )}
          </div>
          <RcmdModal open={rcmdOpen} setOpen={setRcmdOpen} />
          <div className="right-drawer__inner__content">
            <RcmdList />
            <Divider />
            <Data />
          </div>
        </Drawer>
      </div>
    </ThemeProvider>
  );
}

export default Output;
