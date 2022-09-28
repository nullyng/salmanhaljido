import CategoryItem from "./CategoryItem";

function MakeItem(arr) {
    if (arr.length === 0) {
        return (<p>해당 카테고리는 세부항목이 없습니다.</p>)
    }
    else {
        const result = []
        for (let i = 0; i < arr.length; i++) {
            result.push(<CategoryItem CategoryName={arr[i]} />)
        }
        return result
    }
}

function CategoryPanel({arr}) {
    
    return(
        <div className="category__box">
            <div className="category__box--top"><div style={{width:90}}></div>
                <div>&nbsp;&nbsp;선택안함</div>
                <div>하&nbsp;&nbsp;&nbsp;&nbsp;</div>
                <div>&nbsp;중&nbsp;&nbsp;&nbsp;</div>
                <div>&nbsp;상</div>
            </div>
            {MakeItem(arr)}
        </div>
)
}
export default CategoryPanel;

