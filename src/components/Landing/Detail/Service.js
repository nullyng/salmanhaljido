import "styles/Landing/Landing.scss";
import ServiceBox from "./ServiceBox";

function Service() {
    return(
        <div className="service__introduce">
            <div className="service__introduce--textbox">
                <div className="service__bigfont">살만할지도</div><div className="service__font">는</div>
            </div>
            <div className="service__font">이런 서비스를 지원하고 있어요.</div>
            <ServiceBox/>
        </div>
    )
}

export default Service;