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
    <>
      <TelaAtendente />
      {/* <TelaFila /> */}
    </>
  );
}

export default App