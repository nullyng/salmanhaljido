import "animate.css";

import Start from "components/Landing/Start/Start";
import Detail from "components/Landing/Detail/Detail";
import "styles/Landing/Landing.scss";
import "styles/common/animations.scss";

function LandingPage() {
  return (
    <div className="landing">
      <Start />
      <div className="start__back"></div>
      <Detail />
    </div>
  );
}

export default LandingPage;
