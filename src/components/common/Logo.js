import Box from "@mui/material/Box";
import Divider from "@mui/material/Divider";
import { useEffect } from "react";
import { NavLink } from "react-router-dom";

import "styles/common/Logo.scss";

function Logo() {
  useEffect(() => {
    if (document.querySelector(".logo__map--active") !== null) {
      const mapLogo = document.querySelector(".logo__map--active");
      mapLogo.addEventListener("click", () => {
        window.location.reload();
      });
    } else if (document.querySelector(".logo__info--active") !== null) {
      const infoLogo = document.querySelector(".logo__info--active");
      infoLogo.addEventListener("click", () => {
        window.location.reload();
      });
    }
  });

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
