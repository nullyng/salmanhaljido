import mapboxgl from "mapbox-gl";
import MapboxLanguage from "@mapbox/mapbox-gl-language";
import { useEffect, useRef, useState } from "react";
import "mapbox-gl/dist/mapbox-gl.css";

import "styles/Main/Map.scss";
import "styles/Main/Marker.scss";
import Logo from "components/common/Logo";
import TL_SCCO_SIG from "./TL_SCCO_SIG";
import { Button } from "@mui/material";

function Map() {
  const mapContainer = useRef(null);
  const map = useRef(null);
  const [lng, setLng] = useState(127.88);
  const [lat, setLat] = useState(35.9);
  const [zoom, setZoom] = useState(6.4);
  mapboxgl.accessToken = process.env.REACT_APP_MAPBOX_ACCESS_TOKEN;

  const geoJsonData = TL_SCCO_SIG.features.filter(
    (feature) =>
      feature.properties.SIG_CD === "42110" ||
      feature.properties.SIG_CD === "27260" ||
      feature.properties.SIG_CD === "26350"
  );

  const [markers, setMarkers] = useState([
    { title: "대구", longitude: 128.583052, latitude: 35.798838 },
    { title: "서울", longitude: 126.956764, latitude: 37.540705 },
    { title: "울산", longitude: 129.239078, latitude: 35.519301 },
    { title: "대전", longitude: 127.378953, latitude: 36.321655 },
    { title: "제주", longitude: 126.542671, latitude: 33.364805 },
  ]);

  useEffect(() => {
    if (map.current) return; // 지도는 처음 한 번만 초기화

    // 지도 생성
    map.current = new mapboxgl.Map({
      container: mapContainer.current,
      style: "mapbox://styles/young315/cl83zw1gh000414pbt1guz5b6/draft",
      center: [lng, lat],
      zoom: zoom,
    });

    markers.map((marker, index) => {
      // 마커 커스텀
      const el = document.createElement("div");
      el.className = "marker";

      // 클릭 이벤트
      el.addEventListener("click", () => {
        handleClickButton();
      });

      // 마커를 지도에 추가
      new mapboxgl.Marker(el)
        .setLngLat([marker.longitude, marker.latitude])
        .addTo(map.current);
    });

    // 언어 설정
    mapboxgl.setRTLTextPlugin(
      "https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-rtl-text/v0.1.0/mapbox-gl-rtl-text.js"
    );
    const mapboxLanguage = new MapboxLanguage({
      defaultLanguage: "ko",
    });
    map.current.addControl(mapboxLanguage);
  });

  const handleClickButton = () => {
    console.log(geoJsonData);

    geoJsonData.map((item, index) => {
      const data = {
        type: "geojson",
        data: item,
      };

      map.current.addSource(`polygon${index}`, data);

      // 폴리곤을 출력하기 위해 새로운 레이어를 생성한다.
      map.current.addLayer({
        id: `polygon${index}`,
        type: "fill",
        source: `polygon${index}`, // 데이터 소스 레퍼런스
        layout: {},
        paint: {
          "fill-color": "#0080ff", // 해당 컬러로 채운다.
          "fill-opacity": 0.5,
        },
      });

      // 폴리곤 주변에 윤곽선 추가
      map.current.addLayer({
        id: `polygon${index}-outlined`,
        type: "line",
        source: `polygon${index}`,
        layout: {},
        paint: {
          "line-color": "#000",
          "line-width": 3,
        },
      });
    });
  };

  return (
    <div className="custom-map">
      <Logo />
      <div ref={mapContainer} className="map-container" />
    </div>
  );
}

export default Map;
