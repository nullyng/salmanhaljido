import { useState } from "react";
import { Alert, Button, Collapse } from "@mui/material";
import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";

import CustomModal from "components/common/CustomModal";

function CategoryRcmd({ alertOpen }) {
  const [modalOpen, setModalOpen] = useState(false);

  const rcmdList = ["대중교통 정류장", "전기차 충전소", "공용, 민영 주차장"];

  return (
    <div className="category-rcmd">
      <Collapse in={alertOpen}>
        <Alert
          color="primary"
          icon={<ThumbUpOffAltIcon fontSize="inherit" />}
          onClose={() => {}}
        >
          <div className="category-rcmd__content">
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
          </div>
          <div className="category-rcmd__button">
            <Button variant="outlined" onClick={() => setModalOpen(true)}>
              똑같이 적용하기
            </Button>
            <CustomModal
              open={modalOpen}
              setOpen={setModalOpen}
              title="잠깐!"
              content="이미 선택한 카테고리가 전부 초기화돼요.\n그래도 계속하실 건가요?"
              cancelMessage="아니오"
              okMessage="계속 할래요"
            />
          </div>
        </Alert>
      </Collapse>
    </div>
  );
}

export default CategoryRcmd;
