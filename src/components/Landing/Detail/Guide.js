import { useEffect, useState } from "react";

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
      <div className="guide__box" style={{ opacity: (position + 20) / 40 }}>
        <div className="guide__box__text">우리 부부의 현재 환경 설정하기</div>
        <div className="guide__box__image">
          서비스
          <br />
          이미지
        </div>
      </div>
      <div className="guide__box" style={{ opacity: (position + 5) / 100 }}>
        <div className="guide__box__image">
          서비스
          <br />
          이미지
        </div>
        <div className="guide__box__text">
          비슷한 환경의 사람들이 선택한 카테고리를 확인하기
        </div>
      </div>
      <div className="guide__box" style={{ opacity: (position - 200) / 140 }}>
        <div className="guide__box__text">
          카테고리 항목을 취향에 따라 커스텀하기
        </div>
        <div className="guide__box__image">
          서비스
          <br />
          이미지
        </div>
      </div>
      <div className="guide__box" style={{ opacity: (position - 310) / 240 }}>
        <div className="guide__box__image">
          서비스
          <br />
          이미지
        </div>
        <div className="guide__box__text">추천된 지역별로 데이터 확인하기</div>
      </div>
      <div className="guide__box" style={{ opacity: (position - 420) / 330 }}>
        <div className="guide__box__text">주요 정책 확인하기</div>
        <div className="guide__box__image">
          서비스
          <br />
          이미지
        </div>
      </div>
    </div>
  );
}

export default Guide;
