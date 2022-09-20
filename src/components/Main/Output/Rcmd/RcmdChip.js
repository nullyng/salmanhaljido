import * as React from "react";
import Chip from "@mui/material/Chip";
import Stack from "@mui/material/Stack";

import "styles/Main/Output.scss"

function RcmdChip() {
  const handleClick = () => {
    console.info("You clicked the Chip.");
  };

  return (
    <Stack direction="row" spacing={1} className="chip">
      <Chip label="대구광역시 북구" variant="outlined" onClick={handleClick} className="chip__content"/>
    </Stack>
  );
}

export default RcmdChip;
