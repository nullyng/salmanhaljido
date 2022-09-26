import Input from "components/Main/Input/Input";
import CustomMap from "components/Main/Map/CustomMap";
import Output from "components/Main/Output/Output";
import { setRcmdData } from "modules/map";
import { useDispatch, useSelector } from "react-redux";

function MainPage() {
  const rcmdData = useSelector((state) => state.map.rcmdData);

  const dispatch = useDispatch();
  const onSetRcmdData = (rcmdData) => dispatch(setRcmdData(rcmdData));

  return (
    <div>
      <Input rcmdData={rcmdData} onSetRcmdData={onSetRcmdData} />
      <Output />
      <CustomMap rcmdData={rcmdData} />
    </div>
  );
}

export default MainPage;
