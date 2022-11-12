import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";

import defImg from "assets/images/Board/default.png";

function ArticleCard({ title, imageURL, url, summary }) {
  return (
    <Card
      className="article-card"
      onClick={() => window.open(`${url}`, "_blank")}
    >
      <div className="article-card__content">
        <div className="article-card__content__img">
          <CardMedia
            component="img"
            height="170"
            image={imageURL || defImg}
            alt=""
          />
        </div>
        <div className="article-card__content__header">
          <CardContent>
            <div className="article-card__content__header--title">{title}</div>
          </CardContent>
          <CardContent>
            <div className="article-card__content__header--desc">{summary}</div>
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
