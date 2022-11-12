import "styles/Landing/Landing.scss";

function Start() {
  function main(e) {
    window.location.replace("/map");
  }
  return (
    <div className="start">
      <div className="start__bubble">
        <div className="start__bubble--right">어디 사는 게 좋을까?</div>
        <div className="start__bubble--Rtri"></div>
      </div>
      <div className="start__bubble">
        <div className="start__bubble--Ltri"></div>
        <div className="start__bubble--left">영화관이랑~ 마트랑~</div>
      </div>
      <div className="start__bubble">
        <div className="start__bubble--Ltri"></div>
        <div className="start__bubble--left">
          공원도 있어야 하고 지하철도 있어야 해
        </div>
      </div>
      <div className="start__bubble">
        <div className="start__bubble--right">그럼...</div>
        <div className="start__bubble--Rtri"></div>
      </div>
      <div className="start__bubble">
        <div className="start__bubble--end">
          <div className="start__bubble--end1">여기...&nbsp;</div>
          <div className="start__bubble--end2">살만할지도..?</div>
        </div>
        <div className="start__bubble--Rtri"></div>
      </div>
      <div className="start__button">
        <button onClick={main}>
          <span>시작하기</span>
          <span className="ok"></span>
        </button>
      </div>
    </div>
  );
}

export default Start;
