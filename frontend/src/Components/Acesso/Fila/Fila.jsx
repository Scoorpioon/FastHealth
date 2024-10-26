import React, { Suspense, useEffect, useState } from 'react';
import SockJS from 'sockjs-client/dist/sockjs';
import { Stomp } from '@stomp/stompjs';
import '../../../Styles/Fila.scss';
import { useParams } from 'react-router-dom';

const Fila = () => {
  const { dataFila } = useParams();
  const [fila, setFila] = useState();
  const [nomePacientesPassados, setNomePacientesPassados] = useState([]);
  const socket = new SockJS('http://localhost:8080/ws');
  const stompClient = Stomp.over(socket);

  useEffect(() => {
    stompClient.connect({}, (frame) => {
      stompClient.subscribe('/filaWS', (message) => {
        setFila(JSON.parse(message.body));

      });
    });

    return () => {
      if (stompClient) {
        stompClient.disconnect();
      }
    };
  }, []); // Esse bonitão aqui desmonta tudo quando o componente for fechado 

  useEffect(() => {
    console.log(fila);
  }, [fila]);

/*   useEffect(() => {
    setNomePacientesPassados(fila.consultas)
  }, [fila.consultas]) */

  return (
    <section id="secao__Fila">
      <h1>Fila de atendimento</h1>
      <ul className="nome_pacientes">
        {
        fila
          ?
        fila.consultas.map((consulta) => {return <li key={consulta.idConsulta}>{consulta.paciente.nome} <span>Num</span></li>})
          :
        <span>Carregando...</span>
        }
      </ul>
    </section>
  );
};

export default Fila;