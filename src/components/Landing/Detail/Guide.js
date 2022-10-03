import { useEffect, useState } from "react";

import guide1 from "assets/images/Landing/guide1.gif";
import guide2 from "assets/images/Landing/guide2.gif";
import guide3 from "assets/images/Landing/guide3.gif";
import guide4 from "assets/images/Landing/guide4.gif";
import guide5 from "assets/images/Landing/guide5.gif";

function Guide() {
  // 화면 스크롤 코드
  const [position, setPosition] = useState(0);

  function onScroll() {
    console.log(window.scrollY);
    setPosition(window.scrollY);
  }
  useEffect(() => {
    window.addEventListener("scroll", onScroll);
    return () => {
      window.removeEventListener("scroll", onScroll);
    };
  }, []);

  return (
    <div className="guide animate__animated animate__fadeInUp">
      <div className="guide__top">이렇게 사용해요</div>
      <div className="guide__box one" style={{ opacity: (position + 20) / 40 }}>
        <div className="guide__box__text">우리 부부의 현재 환경 설정하기</div>
        <div className="guide__box__image">
          <img src={guide1} />
        </div>
      </div>
      <div className="guide__box two" style={{ opacity: (position + 5) / 100 }}>
        <div className="guide__box__image">
          <img src={guide2} />
        </div>
        <div className="guide__box__text">
          비슷한 환경의 사람들이
          <br />
          선택한 카테고리를 추천받기
        </div>
      </div>
      <div
        className="guide__box three"
        style={{ opacity: (position - 200) / 140 }}
      >
        <div className="guide__box__text">
          카테고리 항목을 취향에 따라 커스텀하기
        </div>
        <div className="guide__box__image">
          <img src={guide3} />
        </div>
      </div>
      <div
        className="guide__box four"
        style={{ opacity: (position - 310) / 240 }}
      >
        <div className="guide__box__image">
          <img src={guide4} />
        </div>
        <div className="guide__box__text">
          추천 지역을 검색하고 <br /> 상세 정보 확인하기
        </div>
      </div>
      <div
        className="guide__box five"
        style={{ opacity: (position - 420) / 330 }}
      >
        <div className="guide__box__text">
          여러 카테고리에서 <br /> 유용한 정보 확인하기
        </div>
        <div className="guide__box__image">
          <img src={guide5} />
        </div>
      </div>
    </div>
  );
}

export default Guide;
