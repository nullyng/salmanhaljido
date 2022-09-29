import { useDispatch } from "react-redux";
import { delCategory } from "modules/category";

import Avatar from "@mui/material/Avatar";
import Chip from "@mui/material/Chip";
import Stack from "@mui/material/Stack";

function CategoryChip({ CategoryName, CategoryLevel }) {
  const dispatch = useDispatch();
  const handleDelete = () => {
    dispatch(delCategory(CategoryName));
  };

  const level = {
    1: "하",
    2: "중",
    3: "상",
  };

  return (
    <Stack className="category-chip" direction="row" spacing={1}>
      <Chip
        avatar={
          CategoryLevel === 1 ? (
            <Avatar className="category-chip__avatar--1">
              {level[CategoryLevel]}
            </Avatar>
          ) : CategoryLevel === 2 ? (
            <Avatar className="category-chip__avatar--2">
              {level[CategoryLevel]}
            </Avatar>
          ) : (
            <Avatar className="category-chip__avatar--3">
              {level[CategoryLevel]}
            </Avatar>
          )
        }
        label={CategoryName}
        variant="outlined"
        onDelete={handleDelete}
      />
    </Stack>
  );
}

export default CategoryChip;
