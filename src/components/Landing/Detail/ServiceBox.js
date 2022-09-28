import "styles/Landing/Landing.scss";
import map from "assets/images/Landing/map.png";
import recommend from "assets/images/Landing/customer.png";
import law from "assets/images/Landing/law.png";

function ServiceBox() {
    return(
        <div className="service__introduce--squarebox">
            <div className="service__introduce--square">
                <img className="service__introduce--image" src={map} alt="map"/>
                <div>내 취향에 맞게<br/>거주지를 추천받기</div>
            </div>
            <div className="service__introduce--square">
                <img className="service__introduce--image" src={recommend} alt="recommend"/>
                <div>다른 사람들이 선택한<br/>카테고리를 추천받기</div>
            </div>
            <div className="service__introduce--square">
            <img className="service__introduce--image" src={law} alt="law"/>
                <div>지역에 따른<br/>정책들 모아보기</div>
            </div>
        </div>
    )
}

export default ServiceBox;