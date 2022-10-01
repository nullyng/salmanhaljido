import Tooltip, { tooltipClasses } from "@mui/material/Tooltip";
import { styled } from "@mui/material/styles";
import { IconButton } from "@mui/material";
import HelpIcon from "@mui/icons-material/Help";

const MyTooltip = styled(({ className, ...props }) => (
  <Tooltip {...props} arrow classes={{ popper: className }} />
))(({ theme }) => ({
  [`& .${tooltipClasses.arrow}`]: {
    color: "#16213E",
  },
  [`& .${tooltipClasses.tooltip}`]: {
    backgroundColor: "#16213E",
    fontSize: "0.9em",
    margin: "1rem",
  },
}));

function CustomTooltip({ content }) {
  return (
    <MyTooltip title={content} placement="top">
      <IconButton>
        <HelpIcon />
      </IconButton>
    </MyTooltip>
  );
}

export default CustomTooltip;
