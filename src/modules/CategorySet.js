/* 액션 타입 */
const ADD = "CategorySet/ADD";
const DEL = "CategorySet/DEL"

/* 액션 생성 함수 */
export const addCategory = (Name, Level) => ({ type: ADD, Name, Level });
export const delCategory = (Name, Level) => ({ type: DEL, Name, Level });

/* 초기 상태. 카테고리 리스트 비어있음.*/
const initialState = {
  MyCategoryList : {},
  CategoryList : {
    "교통" : ["대중교통 이용률", "대중교통 정류장", "전기차 충전소", "공용, 민영주차장"],
    "재난" : ["민방위 대피소"],
    "안전" : ["교통사고", "화재", "범죄", "어린이 보호구역", "여성안심"],
    "의료" : ["의료기관", "약국"],
    "반려동물" : ["동물병원", "동물미용"],
    "교육" : ["유치원", "초, 중, 고등학교", "학원 및 교습소"],
    "문화" : ["미술관, 박물관", "영화관", "도서관", "공연장", "스포츠시설"],
    "생활" : ["음식점", "공원", "마트, 시장", "장애인 편의시설"],
  }
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