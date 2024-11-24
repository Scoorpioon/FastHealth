import { useEffect, useState, useRef, useContext } from 'react';
import { adicionarPacienteAtendido } from '../../Context/Redux/slices/pacientesSlice';
import { useDispatch, useSelector } from 'react-redux';
import { useParams } from 'react-router-dom';
import { Stomp } from '@stomp/stompjs';
import AbreviarNome from '../../Funcs/AbreviarNome';
import SockJS from 'sockjs-client/dist/sockjs';
import '../../Styles/Fila.scss';

const Fila = () => {
  const { dataFila } = useParams();
  const [infoUsuario, setInfoUsuario] = useState(JSON.parse(localStorage.getItem('paciente')));
  const [posicao, setPosicao] = useState();
  const [fila, setFila] = useState();
  const [consultas, setConsultas] = useState();
  const [pacientesAtendidos, setarPacienteAtendido] = useState();
  const [botaoClicado, clicarNoBotao] = useState(false);
  const stompClient = useRef(null);
  const caixaDados = useRef(null);
  const dispatch = useDispatch();
  const audio = new Audio('/SomFila.mp3');

  useEffect(() => {
    const socket = new SockJS('http://localhost:8080/ws');
    stompClient.current = Stomp.over(socket);
    
    stompClient.current.connect({}, (frame) => {
      stompClient.current.subscribe('/filaWS', (message) => {
        setFila(JSON.parse(message.body));
        stompClient.current.send('/app/buscarConsultas', {}, JSON.stringify({"dataConsulta": dataFila}));
      });

      stompClient.current.subscribe('/consultaWS', (message) => {
        setConsultas(JSON.parse(message.body));
      });

      if(stompClient.current && stompClient.current.connected) {
        stompClient.current.send('/app/retornarFilaPorData', {}, JSON.stringify({"dataFila": dataFila}));
      }
    });

    return () => {
      if (stompClient) {
        stompClient.current.disconnect();
      }
    };
  }, []); // Esse bonitão aqui desmonta tudo quando o componente for fechado

  const listarPacientes = () => {
    if(fila) {
      if(fila.consultas.length == 0) {
        return <span className="sem_pacientes">Sem pacientes em espera</span>
      }

      return fila.consultas.map((consulta, pos) => {
        if(consulta.paciente.nome == infoUsuario.data.nome) {
          return(
          <li key={consulta.idConsulta} value="usuario" className="nome_usuario">
            <span>{AbreviarNome(consulta.paciente.nome)}</span>
          </li>
          )
        } else {
          return (
          <li key={consulta.idConsulta}>
            <span>{AbreviarNome(consulta.paciente.nome)}</span>
          </li>
          )
        }
      })
    } else {
      return <span>Carregando...</span>
    }
  }

  const pegarPosicao = () => {
    if(fila) {
      console.log(JSON.parse(localStorage.getItem('paciente')).data);
      fila.consultas.map((consulta, pos) => {
        if(consulta.paciente.nome == JSON.parse(localStorage.getItem('paciente')).data.nome) {
          setPosicao(pos + 1);
        }
      })
    }
  }

  const listarPacientesAtendidos = () => {
    if(consultas) {
      return consultas.map((consulta) => {
        if(consulta.consultaRealizada === true) {
          return(
            <li key={consulta.idConsulta}>{AbreviarNome(consulta.paciente.nome)}</li>
          )
        }
      });
    }
  }

  const mostrarDados = () => {
    if(botaoClicado) {
      caixaDados.current.style.display = 'none';
      clicarNoBotao(false);
    } else {
      caixaDados.current.style.display = 'flex';
      clicarNoBotao(true);
    }
  }

  useEffect(() => {
    pegarPosicao();
    audio.play();

  }, [fila]);

  return (
    <section id="secao__Fila">
      <div id="listas">
        {window.screen.width > 1340 
        ? 
        <div className="dados">
          <span>Tempo médio de atendimento: 20:00</span>
          <span>Posição na fila: {posicao}</span>
        </div>
        :
        <>
          <span className="botao_dados" onClick={mostrarDados}>i</span>
          <div className="dados_compactados" ref={caixaDados}>
            <span>Tempo médio de atendimento: 20:00</span>
            <span>Posição na fila: {posicao}</span>
          </div>
        </>
        }
        <div id="fila">
          <h2>Fila de atendimento</h2>
          <ul className="nome_pacientes">
            {listarPacientes()}
          </ul>
        </div>
        <div id="passados">
          <h2>Pacientes anteriores</h2>
          <ul className="pacientes_passados">
            {listarPacientesAtendidos()}
          </ul>
        </div>
      </div>
    </section>
  );
};

export default Fila;