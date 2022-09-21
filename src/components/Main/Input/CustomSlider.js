import styled from "@emotion/styled";
import { Slider } from "@mui/material";

const CustomSlider = styled(Slider)({
  color: "#4D88C6",
  height: 8,
  marginTop: "3rem",

  "& .MuiSlider-track": {
    border: "none",
    color: "#C4D9F0",
  },
  "& .MuiSlider-rail": {
    color: "#C3C3C3",
  },
  "& .MuiSlider-thumb": {
    height: 20,
    width: 20,
    backgroundColor: "#4D88C6",
    border: "2px solid currentColor",
    "&:focus, &:hover, &.Mui-active, &.Mui-focusVisible": {
      boxShadow: "inherit",
    },
    "&:before": {
      display: "none",
    },
  },
  "& .MuiSlider-valueLabel": {
    lineHeight: 1.2,
    fontSize: 14,
    fontWeight: 600,
    background: "unset",
    padding: 5,
    width: 32,
    height: 32,
    borderRadius: "50% 50% 50% 0",
    backgroundColor: "#4D88C6",
    transformOrigin: "bottom left",
    transform: "translate(50%, -100%) rotate(-45deg) scale(0)",
    "&:before": { display: "none" },
    "&.MuiSlider-valueLabelOpen": {
      transform: "translate(50%, -100%) rotate(-45deg) scale(1)",
    },
    "& > *": {
      transform: "rotate(45deg)",
    },
  },
});

export default CustomSlider;
