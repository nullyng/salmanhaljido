import Box from '@mui/material/Box';
import Slider from '@mui/material/Slider';

const marks = [
  {
    value: 0,
    label: '선택안함',
  },
  {
    value: 1,
    label: '하',
  },
  {
    value: 2,
    label: '중',
  },
  {
    value: 3,
    label: '상',
  },
];

function valueLabelFormat(value) {
  return marks.findIndex((mark) => mark.value === value) + 0;
}

function CategorySlider() {
  return (
    <Box sx={{ width: 150 }}>
      <Slider
        aria-label="Restricted values"
        defaultValue={0}
        valueLabelFormat={valueLabelFormat}
        step={1}
        // valueLabelDisplay="auto" 
        marks
        min={0}
        max={3}
      />
    </Box>
  );
}

export default CategorySlider;