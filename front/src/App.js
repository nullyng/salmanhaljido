import { BrowserRouter, Route, Routes } from "react-router-dom";

import './App.css';
import LandingPage from "pages/LandingPage";
import MainPage from "pages/MainPage";
import BoardPage from "pages/BoardPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<LandingPage />} />
        <Route path="/map" element={<MainPage />} />
        <Route path="/board" element={<BoardPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
