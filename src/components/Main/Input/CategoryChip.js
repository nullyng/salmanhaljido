import Chip from '@mui/material/Chip';
import Stack from '@mui/material/Stack';

function CategoryChip() { 
  const handleDelete = () => {
    console.info('You clicked the delete icon.');
  };

  return (
    <Stack direction="row" spacing={1}>
      <Chip label="임시 칩" variant="outlined" onDelete={handleDelete} />
    </Stack>
  );
}

export default CategoryChip;