import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Panel from './components/Panel';

const App = () => (
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/panel" element={<Panel />} />
    </Routes>
  </BrowserRouter>
);

export default App;
