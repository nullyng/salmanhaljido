/* 액션 타입 */
const ADD_CATEGORY = "category/ADD_CATEGORY";
const DEL_CATEGORY = "category/DEL_CATEGORY";
const INIT_CATEGORY = "category/INIT_CATEGORY";

/* 액션 생성 함수 */
export const addCategory = (value, level) => ({
  type: ADD_CATEGORY,
  value,
  level,
});
export const delCategory = (value) => ({
  type: DEL_CATEGORY,
  value,
});
export const initCategory = () => ({
  type: INIT_CATEGORY,
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
      clone1[action.value] = action.level;
      console.log(clone1);
      return {
        ...state,
        myCategoryList: clone1,
      };
    case DEL_CATEGORY:
      let clone2 = {};
      for (let key in state.myCategoryList) {
        clone2[key] = state.myCategoryList[key];
      }
      delete clone2[action.value];
      console.log(clone2);
      return {
        ...state,
        myCategoryList: clone2,
      };
    case INIT_CATEGORY:
      return {
        ...state,
        myCategoryList: {},
      };
    default:
      return state;
  }
}
export default category;
