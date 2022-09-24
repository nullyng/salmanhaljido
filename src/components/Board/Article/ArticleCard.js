import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import { CardActionArea } from "@mui/material";

import defImg from "assets/images/Board/default.png";

function ArticleCard(dummy) {
  return (
    <Card className="article-card">
      <CardActionArea>
        <div>
          <CardMedia
            component="img"
            height="210"
            image={dummy.img || defImg}
            alt=""
          />
        </div>
        <div>
          <CardContent className="article-card__content">
            <Typography
              variant="body2"
              color="text.secondary"
              className="article-card__content--detail"
            >
              {dummy.title}
            </Typography>
          </CardContent>
        </div>
      </CardActionArea>
    </Card>
  );
}

export default ArticleCard;
