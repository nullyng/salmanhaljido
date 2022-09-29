import * as React from "react";
import {
  Radar,
  RadarChart,
  PolarGrid,
  PolarAngleAxis,
  PolarRadiusAxis,
} from "recharts";

import DetailData from "components/Main/Output/Data/DetailData";

function ChartData({ statistics }) {
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

  return (
    <div className="chart-data">
      {
        statistics.length === 0
          ? <div className="chart-data--no-data">
            <p>
              추천 지역을 선택해주세요.
            </p>
          </div>
          : <div className="char-data--data">
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
                name="Mike"
                dataKey="A"
                stroke="#E94560"
                fill="#E94560"
                fillOpacity={0.6}
              />
            </RadarChart>
            <DetailData />
          </div>
      }
    </div>
  );
}

export default ChartData;
