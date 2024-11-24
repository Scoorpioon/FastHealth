import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { BancoPacientesProvider } from './Context/BancoPacientes';
import TelaRegistroAtendente from './Components/Atendente/TelaRegistroAtendente';
import TelaLoginAtendente from './Components/Atendente/TelaLoginAtendente';
import CriacaoConsultas from './Components/Atendente/CriacaoConsultas';
import TelaAtendente from './Components/Fila/TelaAtendente';
import TelaConsultas from './Components/Paciente/TelaConsultas';
import TelaRegistro from './Components/Paciente/TelaRegistro';
import { Provider } from 'react-redux';
import TelaLogin from './Components/Paciente/TelaLogin';
import TelaFila from './Components/Fila/TelaFila';
import Landing from './Components/Landing';
import Header from './Components/Header';
import store from './Context/Redux/store';
import Fila from './Components/Fila/Fila';
import './Styles/General.scss';

function App() {
  return (
    <Provider store={store}>
      <Router>
              <Routes>
                <Route path='/' element={<Landing />} />
                <Route path='/registro/paciente' element={<TelaRegistro />} />
                <Route path='/registro/atendente' element={<TelaRegistroAtendente />} />
                <Route path='/login/paciente' element={<TelaLogin />} />
                <Route path='/login/atendente' element={<TelaLoginAtendente />} />
                <Route path='/paciente/consultas' element={<TelaConsultas />} />
                <Route path='/painelDoAtendente' element={<TelaAtendente />} />
                <Route path='/fila/:dataFila' element={<TelaFila />} />
                <Route path='/consulta/criar' element={<CriacaoConsultas />} />
              </Routes>
        </Router>
      </Provider>
  );
}

export default App;