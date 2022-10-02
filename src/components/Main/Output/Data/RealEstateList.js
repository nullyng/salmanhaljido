import { useSelector } from "react-redux";

import RealEstateCard from "components/Main/Output/Data/RealEstateCard";

function RealEstateList() {
  const currRegion = useSelector((state) => state.region.currRegion);
  const realEstate = useSelector((state) => state.region.realEstate);

  return (
    <div className="real-estate-ldist">
      {Object.keys(currRegion).length === 0 ? (
        <div className="real-estate-list--no-data">
          <p>추천 지역을 선택해주세요.</p>
        </div>
      ) : (
        <div className="real-estate-list--data">
          {realEstate.map((data, index) => {
            <RealEstateCard key={index} title={data.title} url={data.url} />;
          })}
        </div>
      )}
    </div>
  );
}

export default RealEstateList;
