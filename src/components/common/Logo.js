import * as React from "react";
import Box from "@mui/material/Box";
import Divider from "@mui/material/Divider";
import { NavLink } from "react-router-dom";

import "styles/common/Logo.scss";

function Logo() {
  return (
    <div className="logo">
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          bgcolor: "background.paper",
          "& hr": {
            mx: 0.5,
          },
        }}
      >
        <NavLink
          to="/map"
          className={({ isActive }) =>
            "logo__map" + (isActive ? "--active" : "")
          }
        >
          살만할지도
        </NavLink>
        <Divider orientation="vertical" variant="middle" flexItem />
        <NavLink
          to="/board"
          className={({ isActive }) =>
            "logo__info" + (isActive ? "--active" : "")
          }
        >
          살만할정보
        </NavLink>
      </Box>
    </div>
  );
}

export default Logo;
