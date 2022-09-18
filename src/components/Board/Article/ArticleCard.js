import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import { CardActionArea } from "@mui/material";

function ArticleCard(dummy) {
  return (
    <div className="board-card">
      <img
        src={
          dummy.img || `${process.env.PUBLIC_URL}/assets/images/Board/baby.png`
        }
        alt="image"
        className="board-card__img"
      />
      <div className="board-card__title">
        <p>
          {dummy.title.length < 35
            ? dummy.title
            : dummy.title.slice(0, 35) + "..."}
        </p>
      </div>
    </div>

    // <Card sx={{ maxWidth: 330 }} className="article-card">
    //   <CardActionArea>
    //     <CardMedia
    //       component="img"
    //       height="250"
    //       image="/static/images/cards/contemplative-reptile.jpg"
    //       alt="green iguana"
    //     />
    //     <CardContent>
    //       <Typography variant="body2" color="text.secondary">
    //         {dummy.title}
    //       </Typography>
    //     </CardContent>
    //   </CardActionArea>
    // </Card>
  );
}

export default ArticleCard;
