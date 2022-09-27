import CategorySlider from "./CategorySlider";

function Category0() {
    const list = ["대중교통 이용률", "대중교통 정류장", "전기차 충전소", "공용, 민영주차장"]

    return(
        <div className="category__box">
            <div className="category__box--top"><div style={{width:90}}></div>
            <div>&nbsp;&nbsp;선택안함</div>
            <div>하&nbsp;&nbsp;&nbsp;&nbsp;</div>
            <div>&nbsp;중&nbsp;&nbsp;&nbsp;</div>
            <div>&nbsp;상</div>
            </div>
            <div className="category__box--row">{list[0]}<CategorySlider/></div>
            <div className="category__box--row">{list[1]}<CategorySlider/></div>
            <div className="category__box--row">{list[2]}<CategorySlider/></div>
            <div className="category__box--row">{list[3]}<CategorySlider/></div>
        </div>
    )
}
export default Category0;