import map from "assets/images/Landing/map.png";
import recommend from "assets/images/Landing/customer.png";
import law from "assets/images/Landing/law.png";

function ServiceBox() {
  return (
    <div className="service-box animate__animated animate__fadeInUp">
      <div className="service-box__item">
        <img src={map} alt="map" />
        <p>
          내 취향에 맞게
          <br />
          거주지를 추천받기
        </p>
      </div>
      <div className="service-box__item">
        <img src={recommend} alt="recommend" />
        <p>
          다른 사람들이 선택한
          <br />
          카테고리를 추천받기
        </p>
      </div>
      <div className="service-box__item">
        <img src={law} alt="law" />
        <p>
          임신, 육아, 부동산 등
          <br />
          유용한 정보 모아보기
        </p>
      </div>
    </div>
  );
}

export default ServiceBox;
