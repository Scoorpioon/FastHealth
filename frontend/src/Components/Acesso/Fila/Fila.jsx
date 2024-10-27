import { useEffect, useState, useRef } from 'react';
import { useParams } from 'react-router-dom';
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client/dist/sockjs';
import '../../../Styles/Fila.scss';

const Fila = () => {
  const { dataFila } = useParams();
  const [infoUsuario, setInfoUsuario] = useState(JSON.parse(localStorage.getItem('paciente')));
  const [fila, setFila] = useState();
  const [nomePacientesPassados, setNomePacientesPassados] = useState([]);
  const stompClient = useRef(null);
  const audio = new Audio('/SomFila.mp3');

  useEffect(() => {
    const socket = new SockJS('http://localhost:8080/ws');
    stompClient.current = Stomp.over(socket);
    
    stompClient.current.connect({}, (frame) => {
      stompClient.current.subscribe('/filaWS', (message) => {
        setFila(JSON.parse(message.body));
      });

      if(stompClient.current && stompClient.current.connected) {
        stompClient.current.send('/app/retornarFilaPorData', {}, JSON.stringify({"dataFila": [2024, 8, 3]}));
      }
    });

    console.log(infoUsuario);

    return () => {
      if (stompClient) {
        stompClient.current.disconnect();
      }
    };
  }, []); // Esse bonitão aqui desmonta tudo quando o componente for fechado

  const listarPacientes = () => {
    if(fila) {
      return fila.consultas.map((consulta) => {
        console.log(infoUsuario.data.nome == consulta.paciente.nome);

        if(consulta.paciente.nome == infoUsuario.data.nome) {
          return(
          <li key={consulta.idConsulta} value="usuario" className="nome_usuario">
            <span>{consulta.paciente.nome}</span>
            {/* <span>Num</span> */} <audio src="/SomFila.mp3"></audio>
          </li>
          )
        } else {
          return (
          <li key={consulta.idConsulta}>
            <span>{consulta.paciente.nome}</span>
            {/* <span>Num</span> */} <audio src="/SomFila.mp3"></audio>
          </li>
          )
        }
      })
    } else {
      return <span>Carregando...</span>
    }
  }

  return (
    <section id="secao__Fila">
      <div id="listas">
        <div id="fila">
          <h2>Fila de atendimento</h2>
          <ul className="nome_pacientes">
            {listarPacientes()}
          </ul>
        </div>
        <ul className="pacientes_passados">
          <h2>Pacientes anteriores</h2>
          <li>Gabriel</li>
          <li>Jhonata</li>
          <li>Felippe</li>
        </ul>
      </div>
    </section>
  );
};

export default Fila;