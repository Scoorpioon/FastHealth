import React, { Suspense, useEffect, useState } from 'react';
import SockJS from 'sockjs-client/dist/sockjs';
import { Stomp } from '@stomp/stompjs';
import '../../../Styles/Fila.scss';

const Fila = () => {
    const [fila, setFila] = useState();
    const socket = new SockJS('http://localhost:8080/ws');
    const stompClient = Stomp.over(socket);

    useEffect(() => {
      stompClient.connect({}, () => {
        stompClient.subscribe('/filaWS', (message) => {
          setFila(JSON.parse(message.body));

          console.log(JSON.parse(message.body));
        });

      });

      /* stompClient.send('/app/retornarFilaPorData', {}, {"dataFila": [2024, 8, 3]}); */

      return () => {
        if (stompClient) {
          stompClient.disconnect();
        }
      };
    }, []); // Esse bonitão aqui desmonta tudo quando o componente for fechado

    const pesquisarFila = () => {
      stompClient.send('/app/retornarFilaPorData', {}, JSON.stringify({"dataFila": [2024, 8, 3]}));
    }

    return (
      <section id="secao__Fila">
        <h1>Fila de atendimento</h1>
        <ul className="nome_pacientes">
          {
          fila
            ?
          fila.consultas.map((consulta) => {return <li key={consulta.idConsulta}>{consulta.paciente.nome} <span>{}</span></li>})
            :
          <span>Carregando...</span>
          }

        </ul>
        <div className="caixa__Botoes">
          <button onClick={pesquisarFila}>Atualizar fila</button>
          <button onClick={() => {console.log('Paciente passado')}}>Passar paciente na fila</button>
        </div>
      </section>
    );
};

export default Fila;