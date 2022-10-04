import Backdrop from "@mui/material/Backdrop";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import { Grid, Rating } from "@mui/material";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import { setSurvey } from "modules/survey";
import { recommendCategory } from "api/category";

const labels = {
  1: "별로예요.",
  2: "만족하지 않아요.",
  3: "그저 그래요.",
  4: "만족해요.",
  5: "매우 만족해요!",
};

function RcmdModal({ open, setOpen }) {
  const [value, setValue] = useState(5);

  const userInfo = useSelector((state) => state.input.userInfo);
  const myCategoryList = useSelector((state) => state.category.myCategoryList);

  const dispatch = useDispatch();
  const onSetSurvey = (survey) => dispatch(setSurvey(survey));

  const handleSubmit = () => {
    const btn = document.getElementById("btn");
    const btnText = document.getElementById("btnText");

    btnText.innerHTML = "제출 완료 :D";
    btn.classList.add("active");

    const apiData = {
      married: userInfo.isMarried,
      hasPets: userInfo.hasPets,
      hasCar: userInfo.hasCar,
      hasChildren: userInfo.hasChildren,
      rating: value,
    };

    Object.keys(myCategoryList).map((category) => {
      apiData[category] = myCategoryList[category];
    });

    recommendCategory(apiData, (res) => {
      console.log(res);
    });

    setTimeout(() => {
      setOpen(false);
      onSetSurvey(true);
    }, 3000);
  };

  return (
    <div>
      <Modal
        className="rcmd-modal"
        open={open}
        onClose={() => setOpen(false)}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          <Box>
            <Grid
              container
              direction="column"
              justifyContent="center"
              textAlign="center"
              flexWrap="nowrap"
            >
              <Grid className="rcmd-modal__title" item>
                <h2>검색 결과에 만족하시나요?</h2>
                <p>응답 결과는 다른 이용자들의 주거지 추천에 사용됩니다.</p>
              </Grid>
              <Grid className="rcmd-modal__content" item>
                {value !== null && (
                  <div className="rating-label">{labels[value]}</div>
                )}
                <Rating
                  defaultValue={5}
                  value={value}
                  precision={1}
                  size="large"
                  onChange={(event, newValue) => setValue(newValue)}
                />
                <div className="section">
                  <button id="btn" onClick={handleSubmit}>
                    <p id="btnText">제출하기</p>
                    <div className="checked">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 50 50"
                      >
                        <path
                          fill="transparent"
                          d="M14.1 27.2l7.1 7.2 16.7-16.8"
                        ></path>
                      </svg>
                    </div>
                  </button>
                </div>
              </Grid>
            </Grid>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}

export default RcmdModal;
