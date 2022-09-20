import Start from "components/Landing/Start/Start";
import Detail from "components/Landing/Detail/Detail";
import "styles/Landing/Landing.scss";

function LandingPage() {
  return (
    <div className='Landing'>
      <Start/>
      <div className="start__back"></div>
      <Detail/>
    </div>
  );
}

export default LandingPage;