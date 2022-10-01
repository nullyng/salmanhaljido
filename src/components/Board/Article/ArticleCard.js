import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";

import defImg from "assets/images/Board/default.png";

function ArticleCard({ title, imageURL, url, summary }) {
  return (
    <Card
      className="article-card"
      onClick={() => window.open(`${url}`, "_blank")}
    >
      <div className="article-card__btn article-card__front">
        <div>
          <CardMedia
            component="img"
            height="170"
            image={imageURL || defImg}
            alt=""
          />
        </div>
        <div className="article-card__cntr">
          <CardContent className="article-card__cntr--content">
            <Typography
              variant="body2"
              color="text.secondary"
              className="article-card__cntr--detail"
            >
              {title}
            </Typography>
          </CardContent>
        </div>
      </div>
      <div className="article-card__btn article-card__back">
        <CardContent>
          <Typography>{summary}</Typography>
        </CardContent>
      </div>
    </Card>
  );
}

export default ArticleCard;
