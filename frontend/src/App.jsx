import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import TelaRegistroAtendente from './Components/Acesso/Atendente/TelaRegistroAtendente';
import TelaRegistroPaciente from './Components/Acesso/Paciente/TelaRegistro';
import TelaLoginAtendente from './Components/Acesso/Atendente/TelaLoginAtendente';
import TelaAtendente from './Components/Acesso/Fila/TelaAtendente';
import TelaLogin from './Components/Acesso/Paciente/TelaLogin';
import TelaFila from './Components/Acesso/Fila/TelaFila';
import Landing from './Components/Landing';
import Header from './Components/Header';
import Fila from './Components/Acesso/Fila/Fila';
import './Styles/General.scss';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<Landing />} />
        <Route path='/login/paciente' element={<TelaLogin />} />
        <Route path='/login/atendente' element={<TelaLoginAtendente />} />
        <Route path='/painelDoAtendente' element={<TelaAtendente />} />
        <Route path='/fila/:dataFila' element={<TelaFila />} />
      </Routes>
    </Router>
  );
}

export default App