import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import TelaRegistroAtendente from './Components/Acesso/Atendente/TelaRegistroAtendente';
import TelaLoginAtendente from './Components/Acesso/Atendente/TelaLoginAtendente';
import TelaRegistroPaciente from './Components/Acesso/Paciente/TelaRegistro';
import TelaLogin from './Components/Acesso/Paciente/TelaLogin';
import TelaFila from './Components/Acesso/Fila/TelaFila';
import Fila from './Components/Acesso/Fila/Fila';
import Header from './Components/Header';
import TelaAtendente from './Components/Acesso/Fila/TelaAtendente';
import './Styles/General.scss';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<span>a</span>} />
        <Route path='/painelDoAtendente' element={<TelaAtendente />} />
        <Route path='/fila/:dataFila' element={<TelaFila />} />
      </Routes>
    </Router>
  );
}

export default App