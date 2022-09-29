import { useDispatch } from "react-redux";
import { delCategory } from "modules/CategorySet";

import Avatar from "@mui/material/Avatar";
import Chip from "@mui/material/Chip";
import Stack from "@mui/material/Stack";

function CategoryChip({ CategoryName, CategoryLevel }) {
  const dispatch = useDispatch();
  const handleDelete = () => {
    dispatch(delCategory(CategoryName));
  };

  return (
    <Stack direction="row" spacing={1}>
      <Chip
        avatar={<Avatar>{CategoryLevel}</Avatar>}
        label={CategoryName}
        variant="outlined"
        onDelete={handleDelete}
      />
    </Stack>
  );
}

export default CategoryChip;
