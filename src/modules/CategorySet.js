/* 액션 타입 */
const ADD = "CategorySet/ADD";
const DEL = "CategorySet/DEL"

/* 액션 생성 함수 */
export const addCategory = (Name, Level) => ({ type: ADD, Name, Level });
export const delCategory = (Name, Level) => ({ type: DEL, Name, Level });

/* 초기 상태. 카테고리 리스트 비어있음.*/
const initialState = {
  MyCategoryList : {},
};

function CategorySet (state = initialState, action) {
  switch (action.type) {
    case ADD:
      state.MyCategoryList[action.Name] = action.Level
      return {
        ...state,
        MyCategoryList:state.MyCategoryList
      };
    case DEL:
      delete state.MyCategoryList[action.Name]
      return {
        ...state,
        MyCategoryList:state.MyCategoryList
      };
    default:
      return state;
    }
}
export default CategorySet;