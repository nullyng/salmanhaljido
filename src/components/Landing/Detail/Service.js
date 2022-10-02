import ServiceBox from "components/Landing/Detail/ServiceBox";

function Service() {
  return (
    <div className="service">
      <div className="animate__animated animate__fadeInUp">
        <div className="service__top">
          <span>살만할지도</span>
          <span>는</span>
        </div>
        <div className="service__bottom">이런 서비스를 지원하고 있어요.</div>
      </div>
      <ServiceBox />
    </div>
  );
}

export default Service;
