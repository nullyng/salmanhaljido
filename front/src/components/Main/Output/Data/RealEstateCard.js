import ArrowForwardIcon from "@mui/icons-material/ArrowForward";

import defImg from "assets/images/Main/default.png";

function RealEstateCard({ title, imageURL, url }) {
  const handleClick = () => {
    window.open(url);
  };

  return (
    <div className="real-estate-card" onClick={handleClick}>
      <div className="real-estate-card__image">
        <img src={imageURL || defImg} alt="부동산 뉴스" />
      </div>
      <div className="real-estate-card__content">{title}</div>
      <div className="overlay">
        <div className="overlay__title">
          <ArrowForwardIcon />
          <h3>자세히 보기</h3>
        </div>
        <p>뉴스를 읽으려면 클릭하세요.</p>
      </div>
    </div>
  );
}

export default RealEstateCard;
