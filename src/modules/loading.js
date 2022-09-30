const SET_LOADING = "loading/SET_LOADING";

export const setLoading = (loading) => ({ type: SET_LOADING, loading });

const initialState = {
  loading: false,
};

export default function loading(state = initialState, action) {
  switch (action.type) {
    case SET_LOADING:
      return {
        ...state,
        loading: action.loading,
      };
    default:
      return state;
  }
}
