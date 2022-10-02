const SET_SURVEY = "survey/SET_SURVEY";

export const setSurvey = (survey) => ({ type: SET_SURVEY, survey });

const initialState = {
  survey: true,
};

export default function survey(state = initialState, action) {
  switch (action.type) {
    case SET_SURVEY:
      return {
        ...state,
        survey: action.survey,
      };
    default:
      return state;
  }
}
