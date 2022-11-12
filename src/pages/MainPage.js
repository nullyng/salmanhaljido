import { useDispatch, useSelector } from "react-redux";

import Input from "components/Main/Input/Input";
import CustomMap from "components/Main/Map/CustomMap";
import Output from "components/Main/Output/Output";
import Loading from "components/common/Loading";

function MainPage() {
  const loading = useSelector((state) => state.loading.loading);
  return (
    <div>
      {loading && <Loading />}
      <Input />
      <Output />
      <CustomMap />
    </div>
  );
}

export default MainPage;
