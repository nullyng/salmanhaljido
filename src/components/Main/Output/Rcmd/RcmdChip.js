import * as React from "react";
import { Button, Rating, ThemeProvider } from "@mui/material";
import StarIcon from "@mui/icons-material/Star";
import styled from "@emotion/styled";

import "styles/Main/Output.scss";
import theme from "components/common/theme";

const StyledRating = styled(Rating)({});

function RcmdChip({ index, name, code, score, handleClick }) {
  return (
    <ThemeProvider theme={theme}>
      <Button className={`chip ${code}`} onClick={handleClick}>
        <div className="chip__content">
          <div className="chip__content__data">
            <span className="chip__content__data__ranking">{index + 1}</span>
            <span className="chip__content__data__region">{name}</span>
          </div>
          <div className="chip__content__rating">
            <Rating
              name="read-only"
              defaultValue={score}
              precision={0.1}
              icon={<StarIcon />}
              readOnly
            />
          </div>
        </div>
      </Button>
    </ThemeProvider>
  );
}

export default RcmdChip;
