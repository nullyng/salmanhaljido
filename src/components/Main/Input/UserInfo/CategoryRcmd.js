import { useState } from "react";
import {
  Alert,
  AlertTitle,
  Button,
  createTheme,
  Grow,
  ThemeProvider,
} from "@mui/material";
import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";

import CustomModal from "components/common/CustomModal";
import { useDispatch } from "react-redux";
import { categoryDetail } from "components/Main/Input/Category/categoryList";
import { addCategory, initCategory } from "modules/category";

const theme = createTheme({
  palette: {
    primary: {
      main: "#e94560",
    },
  },
  typography: {
    fontFamily: "EsamanruMedium",
  },
});

function CategoryRcmd({ title, alertOpen, setAlertOpen, rcmdList }) {
  const [modalOpen, setModalOpen] = useState(false);

  const dispatch = useDispatch();
  const onInitCategory = () => dispatch(initCategory());
  const onAddCategory = (value, level) => dispatch(addCategory(value, level));

  const handleClickButton = () => {
    // 똑같이 적용하기
    onInitCategory();
    rcmdList["high"].map((item) => {
      onAddCategory(item, "high");
    });
    rcmdList["middle"].map((item) => {
      onAddCategory(item, "middle");
    });
    rcmdList["low"].map((item) => {
      onAddCategory(item, "low");
    });
  };

  return (
    <div className="category-rcmd">
      <ThemeProvider theme={theme}>
        <Grow in={alertOpen}>
          <Alert
            color="primary"
            icon={<ThumbUpOffAltIcon fontSize="inherit" />}
            onClose={() => setAlertOpen(false)}
          >
            <AlertTitle className="category-rmcd__title">{title}</AlertTitle>
            <div className="category-rcmd__content">
              {"비슷한 환경의 사용자들은 "}
              <b>
                {`${rcmdList["high"]
                  .map((item, index) => {
                    const name = categoryDetail[item];
                    if (index == rcmdList["high"].length - 1) {
                      return name;
                    }
                    return name + ", ";
                  })
                  .join("")}`}
              </b>
              {"을(를) 가장 중요한 카테고리로, "}
              <b>
                {`${rcmdList["middle"]
                  .map((item, index) => {
                    const name = categoryDetail[item];
                    if (index == rcmdList["middle"].length - 1) {
                      return name;
                    }
                    return name + ", ";
                  })
                  .join("")}`}
              </b>
              {"을(를) 중 카테고리, "}
              <b>
                {`${rcmdList["low"]
                  .map((item, index) => {
                    const name = categoryDetail[item];
                    if (index == rcmdList["low"].length - 1) {
                      return name;
                    }
                    return name + ", ";
                  })
                  .join("")}`}
              </b>
              {"을(를) 하 카테고리로 골랐어요."}
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
                handleClickButton={handleClickButton}
              />
            </div>
          </Alert>
        </Grow>
      </ThemeProvider>
    </div>
  );
}

export default CategoryRcmd;
