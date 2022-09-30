import { useDispatch } from "react-redux";
import Avatar from "@mui/material/Avatar";
import Chip from "@mui/material/Chip";
import Stack from "@mui/material/Stack";
import { Zoom } from "@mui/material";

import { delCategory } from "modules/category";
import { categoryDetail } from "components/Main/Input/Category/categoryList";

function CategoryChip({ categoryValue, categoryLevel }) {
  const dispatch = useDispatch();
  const handleDelete = () => {
    dispatch(delCategory(categoryValue));
  };

  const level = {
    1: "하",
    2: "중",
    3: "상",
  };

  return (
    <Zoom in={true}>
      <Stack className="category-chip" direction="row" spacing={1}>
        <Chip
          avatar={
            categoryLevel === "low" ? (
              <Avatar className="category-chip__avatar--1">
                {level[categoryLevel]}
              </Avatar>
            ) : categoryLevel === "middle" ? (
              <Avatar className="category-chip__avatar--2">
                {level[categoryLevel]}
              </Avatar>
            ) : (
              <Avatar className="category-chip__avatar--3">
                {level[categoryLevel]}
              </Avatar>
            )
          }
          label={categoryDetail[categoryValue]}
          variant="outlined"
          onDelete={handleDelete}
        />
      </Stack>
    </Zoom>
  );
}

export default CategoryChip;
