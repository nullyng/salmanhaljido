import { Alert, Collapse } from "@mui/material";
import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";

function CategoryRcmd({ open }) {
  const rcmdList = ["대중교통 정류장", "전기차 충전소", "공용, 민영 주차장"];

  return (
    <div className="category-rcmd">
      <Collapse in={open}>
        <Alert
          color="primary"
          icon={<ThumbUpOffAltIcon fontSize="inherit" />}
          onClose={() => {}}
        >
          {`비슷한 환경의 사용자들은 `}
          <b>
            {`${rcmdList
              .map((item, index) => {
                if (index == rcmdList.length - 1) {
                  return item;
                }
                return item + ", ";
              })
              .join("")}`}
          </b>
          {`을 선택했어요!`}
        </Alert>
      </Collapse>
    </div>
  );
}

export default CategoryRcmd;
