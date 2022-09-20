import "styles/Landing/Landing.scss";

function Guide() {
    return(
        <div className="service__guide">
            <div className="service__font">이렇게 사용해요</div>
            <div className="service__guide--box">
                <div className="service__guide--text">우리 부부의 현재 환경 설정하기</div>
                <div className="service__guide--image">서비스<br/>이미지</div>                
            </div>
            <div className="service__guide--box">
                <div className="service__guide--image">서비스<br/>이미지</div>                
                <div className="service__guide--text">비슷한 환경의 사람들이 선택한 카테고리를 확인하기</div>
            </div>
            <div className="service__guide--box">
                <div className="service__guide--text">카테고리 항목을 취향에 따라 커스텀하기</div>
                <div className="service__guide--image">서비스<br/>이미지</div>                
            </div>
            <div className="service__guide--box">
                <div className="service__guide--image">서비스<br/>이미지</div>                
                <div className="service__guide--text">추천된 지역별로 데이터 확인하기</div>
            </div>
            <div className="service__guide--box">
                <div className="service__guide--text">주요 정책 확인하기</div>
                <div className="service__guide--image">서비스<br/>이미지</div>                
            </div>
        </div>
    )
}

export default Guide;