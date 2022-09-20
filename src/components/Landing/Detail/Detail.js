import "styles/Landing/Landing.scss";

const { default: Guide } = require("./Guide");
const { default: Service } = require("./Service");

function Detail() {
    const scrollToTop = () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        })
    }
    return (
        <div className="service">
            <Service/>
            <Guide/>
            <div className="service__starttext">
                시작하기 버튼을 통해&nbsp;
                <div className="service__starttext--red">지금 바로 시작&nbsp;</div>
                해보세요!
            </div>
            <button className="service__upbutton" onClick={scrollToTop}>↑</button>
        </div>
    )
}

export default Detail;