import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";

import RealEstateCard from "components/Main/Output/Data/RealEstateCard";
import { getRealEstate } from "api/rcmd";

function RealEstateList() {
  const navigate = useNavigate();
  const [realEstate, setRealEstate] = useState([]);

  const currRegion = useSelector((state) => state.region.currRegion);

  const handleClick = () => {
    navigate("/board");
  };

  useEffect(() => {
    getRealEstate(currRegion.addr, (res) => {
      setRealEstate(res.data.newsList);
    });
  }, [currRegion]);

  return (
    <div className="real-estate-list">
      {Object.keys(currRegion).length === 0 ? (
        <div className="real-estate-list--no-data">
          <p>추천 지역을 선택해주세요.</p>
        </div>
      ) : realEstate.length === 0 ? (
        <div className="real-estate-list--no-data" onClick={handleClick}>
          <p>해당 지역의 부동산 뉴스를 찾지 못했어요.</p>
          <div>
            <ArrowForwardIcon />
            <p>전체 부동산 뉴스 보러 가기</p>
          </div>
        </div>
      ) : (
        <div className="real-estate-list--data">
          {realEstate.map((data, index) => (
            <RealEstateCard
              key={index}
              title={data.title}
              imageURL={data.imageURL}
              url={data.url}
            />
          ))}
        </div>
      )}
    </div>
  );
}

export default RealEstateList;
