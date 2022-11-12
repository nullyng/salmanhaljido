import "styles/common/Loading.scss";
import spinner from "assets/images/spinner.gif";

function Loading() {
  return (
    <div className="loading">
      <img
        className="loading__spinner"
        src={spinner}
        alt="로딩 중"
        width="5%"
      />
    </div>
  );
}

export default Loading;
