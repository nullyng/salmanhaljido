import { Fab } from "@mui/material";
import ArrowUpwardIcon from "@mui/icons-material/ArrowUpward";
import { useNavigate } from "react-router-dom";

import Guide from "components/Landing/Detail/Guide";
import Service from "components/Landing/Detail/Service";

function Detail() {
  const navigate = useNavigate();

  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <div className="detail">
      <Service />
      <Guide />
      <div className="detail__starttext">
        시작하기 버튼을 통해&nbsp;
        <div
          className="detail__starttext--red"
          onClick={() => navigate("/map")}
        >
          지금 바로 시작&nbsp;
        </div>
        해보세요!
      </div>
      <Fab color="primary" aria-label="add" onClick={scrollToTop}>
        <ArrowUpwardIcon />
      </Fab>
    </div>
  );
}

export default Detail;
