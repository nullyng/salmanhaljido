import mapboxgl from "mapbox-gl";
import MapboxLanguage from "@mapbox/mapbox-gl-language";
import { useEffect, useRef, useState } from "react";

import "styles/Main/Map.scss";

function Map() {
  const mapContainer = useRef(null);
  const map = useRef(null);
  const [lng, setLng] = useState(127.51);
  const [lat, setLat] = useState(36.38);
  const [zoom, setZoom] = useState(6.8);
  mapboxgl.accessToken = process.env.REACT_APP_MAPBOX_ACCESS_TOKEN;

  useEffect(() => {
    if (map.current) return; // 지도는 처음 한 번만 초기화

    // 지도 생성
    map.current = new mapboxgl.Map({
      container: mapContainer.current,
      style: "mapbox://styles/young315/cl7qz0k2s000914pnsrpjxpkn",
      center: [lng, lat],
      zoom: zoom
    });

    // 언어 설정
    mapboxgl.setRTLTextPlugin('https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-rtl-text/v0.1.0/mapbox-gl-rtl-text.js');
    const mapboxLanguage = new MapboxLanguage({
      defaultLanguage: 'ko'
    });
    map.current.addControl(mapboxLanguage);
  });

  return (
    <div>
      <div ref={mapContainer} className="map-container" />
    </div>
  )
}

export default Map;