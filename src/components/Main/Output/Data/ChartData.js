import {
  Radar,
  RadarChart,
  PolarGrid,
  PolarAngleAxis,
  PolarRadiusAxis,
} from "recharts";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

import DetailData from "components/Main/Output/Data/DetailData";

const categoryName = [
  "traffic",
  "calamity",
  "safety",
  "medical",
  "pet",
  "education",
  "culture",
  "life",
];

function ChartData() {
  const [currCategory, setCurrCategory] = useState(0); // 초기에 보여주는 데이터는 교통으로 고정

  const currRegion = useSelector((state) => state.region.currRegion);

  const data = [
    {
      subject: "교통",
      A:
        Object.keys(currRegion).length === 0
          ? 100
          : currRegion.category[categoryName[0] + "Score"],
      fullMark: 100,
    },
    {
      subject: "재난",
      A:
        Object.keys(currRegion).length === 0
          ? 100
          : currRegion.category[categoryName[1] + "Score"],
      fullMark: 100,
    },
    {
      subject: "안전",
      A:
        Object.keys(currRegion).length === 0
          ? 100
          : currRegion.category[categoryName[2] + "Score"],
      fullMark: 100,
    },
    {
      subject: "의료",
      A:
        Object.keys(currRegion).length === 0
          ? 100
          : currRegion.category[categoryName[3] + "Score"],
      fullMark: 100,
    },
    {
      subject: "반려동물",
      A:
        Object.keys(currRegion).length === 0
          ? 100
          : currRegion.category[categoryName[4] + "Score"],
      fullMark: 100,
    },
    {
      subject: "교육",
      A:
        Object.keys(currRegion).length === 0
          ? 100
          : currRegion.category[categoryName[5] + "Score"],
      fullMark: 100,
    },
    {
      subject: "문화",
      A:
        Object.keys(currRegion).length === 0
          ? 100
          : currRegion.category[categoryName[6] + "Score"],
      fullMark: 100,
    },
    {
      subject: "생활",
      A:
        Object.keys(currRegion).length === 0
          ? 100
          : currRegion.category[categoryName[7] + "Score"],
      fullMark: 100,
    },
  ];

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
  }, [currCategory, currRegion]);

  return (
    <div className="chart-data">
      {Object.keys(currRegion).length === 0 ? (
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
          <DetailData
            category={currCategory}
            detail={
              Object.keys(currRegion).length === 0
                ? 100
                : currRegion.category[categoryName[currCategory]]
            }
          />
        </div>
      )}
    </div>
  );
}

export default ChartData;
