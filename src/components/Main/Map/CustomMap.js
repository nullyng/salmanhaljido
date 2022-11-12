import mapboxgl from "mapbox-gl";
import { useEffect, useRef, useState } from "react";
import "mapbox-gl/dist/mapbox-gl.css";

import "styles/Main/Map.scss";
import "styles/Main/Marker.scss";
import Logo from "components/common/Logo";
import TL_SCCO_SIG from "components/Main/Map/TL_SCCO_SIG";
import { useDispatch, useSelector } from "react-redux";
import { setCurrMap, setMarkers } from "modules/map";
import { setCurrRegion } from "modules/region";

function CustomMap() {
  const mapContainer = useRef(null);
  const map = useRef(null);
  const [lng, setLng] = useState(127.75);
  const [lat, setLat] = useState(35.9);
  const [zoom, setZoom] = useState(6.2);
  mapboxgl.accessToken = process.env.REACT_APP_MAPBOX_ACCESS_TOKEN;

  const rcmdData = useSelector((state) => state.region.rcmdData);

  const dispatch = useDispatch();
  const onSetCurrMap = (currMap) => dispatch(setCurrMap(currMap));
  const onSetMarkers = (markers) => dispatch(setMarkers(markers));
  const onSetCurrRegion = (currRegion) => dispatch(setCurrRegion(currRegion));

  useEffect(() => {
    // if (map.current) return; // 지도는 처음 한 번만 초기화

    // 지도 생성
    map.current = new mapboxgl.Map({
      container: mapContainer.current,
      style: "mapbox://styles/young315/cl83zw1gh000414pbt1guz5b6/draft",
      center: [lng, lat],
      zoom: zoom,
    });

    onSetCurrMap(map.current);
  });

  useEffect(() => {
    // 선택 초기화 시 지도에 출력되어 있는 마커들을 삭제하기 위해 마커들의 정보를 배열로 저장
    const markerList = [];

    // 마커 생성
    map.current.on("load", () => {
      rcmdData.map((data, index) => {
        const el = document.createElement("div");
        el.className = "marker";

        const div = document.createElement("div");
        div.className = "text-wrapper";

        const addrList = data.addr.split(" ");
        addrList.map((addr) => {
          const addrEl = document.createElement("span");
          addrEl.textContent = addr;
          addrEl.className = "addr";
          div.appendChild(addrEl);
        });

        el.appendChild(div);

        // 마커를 지도에 추가
        const marker = new mapboxgl.Marker(el, {
          anchor: "bottom",
        })
          .setLngLat([data.lng, data.lat])
          .addTo(map.current);

        // 클릭 이벤트 등록
        marker.getElement().addEventListener("click", () => {
          onSetCurrRegion(data);

          map.current.flyTo({
            center: [data.lng, data.lat],
            duration: 600,
            essential: true,
            zoom: 12,
          });
        });

        markerList.push(marker);

        // 폴리곤 출력
        const item = TL_SCCO_SIG.features.filter(
          (feature) => feature.properties.SIG_CD === data.code.substr(0, 5)
        );

        const geoJsonData = {
          type: "geojson",
          data: item[0],
        };

        map.current.addSource(`polygon${index}`, geoJsonData);

        // 폴리곤을 출력하기 위해 새로운 레이어를 생성한다.
        map.current.addLayer({
          id: `polygon${index}`,
          type: "fill",
          source: `polygon${index}`, // 데이터 소스 레퍼런스
          layout: {},
          paint: {
            "fill-color": "#E94560", // 해당 컬러로 채운다.
            "fill-opacity": 0.25,
          },
        });

        // 폴리곤 주변에 윤곽선 추가
        map.current.addLayer({
          id: `polygon${index}-outlined`,
          type: "line",
          source: `polygon${index}`,
          layout: {},
          paint: {
            "line-color": "#E94560",
            "line-width": 3,
          },
        });
      });

      map.current.flyTo({
        center: [rcmdData[0].lng, rcmdData[0].lat],
        duration: 600,
        essential: true,
        zoom: 12,
      });

      onSetMarkers(markerList);
    });
  }, [rcmdData]);

  return (
    <div className="custom-map">
      <Logo />
      <div ref={mapContainer} className="map-container" />
    </div>
  );
}

export default CustomMap;
