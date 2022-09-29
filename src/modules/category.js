/* 액션 타입 */
const ADD_CATEGORY = "category/ADD_CATEGORY";
const DEL_CATEGORY = "category/DEL_CATEGORY";

/* 액션 생성 함수 */
export const addCategory = (name, level) => ({
  type: ADD_CATEGORY,
  name,
  level,
});
export const delCategory = (name, level) => ({
  type: DEL_CATEGORY,
  name,
  level,
});

/* 초기 상태. 카테고리 리스트 비어있음.*/
const initialState = {
  myCategoryList: {},
};

function category(state = initialState, action) {
  switch (action.type) {
    case ADD_CATEGORY:
      let clone1 = {};
      for (let key in state.myCategoryList) {
        clone1[key] = state.myCategoryList[key];
      }
      clone1[action.name] = action.level;
      return {
        ...state,
        myCategoryList: clone1,
      };
    case DEL_CATEGORY:
      let clone2 = {};
      for (let key in state.myCategoryList) {
        clone2[key] = state.myCategoryList[key];
      }
      delete clone2[action.name];
      return {
        ...state,
        myCategoryList: clone2,
      };
    default:
      return state;
  }
}
export default category;
