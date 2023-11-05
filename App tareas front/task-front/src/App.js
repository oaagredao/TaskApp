
import './App.css';
import LogginInicio from './components/LogginInicio/LogginInicio';
import MainInterfaz from './components/MainInterfaz/MainInterfaz';
import Principal from './components/Principal/Principal';
import Registro from './components/Registro/Registro';
// router dom
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/"  element={<Principal/>} />
        <Route path="/registro" element={<Registro/>} />
        <Route path="/login" element={<LogginInicio/>} />
        <Route path="/main" element={<MainInterfaz/>} />

      </Routes>
    </BrowserRouter>
  );
}

export default App;
