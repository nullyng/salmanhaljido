import {
  Radar,
  RadarChart,
  PolarGrid,
  PolarAngleAxis,
  PolarRadiusAxis,
} from "recharts";
import { useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";

import DetailData from "components/Main/Output/Data/DetailData";

function ChartData() {
  const data = [
    {
      subject: "교통",
      A: 100,
      fullMark: 100,
    },
    {
      subject: "재난",
      A: 75,
      fullMark: 100,
    },
    {
      subject: "안전",
      A: 80,
      fullMark: 100,
    },
    {
      subject: "의료",
      A: 50,
      fullMark: 100,
    },
    {
      subject: "반려동물",
      A: 25,
      fullMark: 100,
    },
    {
      subject: "교육",
      A: 65,
      fullMark: 100,
    },
    {
      subject: "문화",
      A: 80,
      fullMark: 100,
    },
    {
      subject: "생활",
      A: 20,
      fullMark: 100,
    },
  ];

  const [currCategory, setCurrCategory] = useState(0); // 초기에 보여주는 데이터는 교통으로 고정

  const currRegion = useSelector((state) => state.region.currRegion);

  useEffect(() => {
    const categories = document.getElementsByClassName(
      "recharts-polar-angle-axis-tick"
    );

    for (let i = 0; i < categories.length; i++) {
      // 각 카테고리를 클릭하면 currCategory를 현재 카테고리 인덱스로 설정
      categories[i].addEventListener("click", () => {
        setCurrCategory(i);
      });
    }
  }, [currCategory]);

  return (
    <div className="chart-data">
      {currRegion === "" || currRegion === undefined ? (
        <div className="chart-data--no-data">
          <p>추천 지역을 선택해주세요.</p>
        </div>
      ) : (
        <div className="char-data--data">
          <RadarChart
            cx={180}
            cy={180}
            outerRadius={130}
            width={360}
            height={360}
            data={data}
          >
            <PolarGrid />
            <PolarAngleAxis dataKey="subject" />
            <PolarRadiusAxis />
            <Radar
              name="data"
              dataKey="A"
              stroke="#E94560"
              fill="#E94560"
              fillOpacity={0.6}
            />
          </RadarChart>
          <DetailData category={currCategory} />
        </div>
      )}
    </div>
  );
}

export default ChartData;
