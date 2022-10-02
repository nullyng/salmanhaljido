import * as React from "react";
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";

import defImg from "assets/images/Main/default.png";

function RealEstateCard({ title, url }) {
  const handleClick = () => {
    window.open(url);
  };

  return (
    <Card sx={{ display: "flex" }} onClick={handleClick}>
      <CardMedia
        component="img"
        sx={{ width: 151 }}
        image={defImg}
        alt="Live from space album cover"
      />
      <Box sx={{ display: "flex", flexDirection: "column" }}>
        <CardContent sx={{ flex: "1 0 auto" }}>
          <Typography
            variant="subtitle1"
            color="text.secondary"
            component="div"
          >
            {title}
          </Typography>
        </CardContent>
      </Box>
    </Card>
  );
}

export default RealEstateCard;
