import React, { useState, useEffect } from 'react';
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client/dist/sockjs';
import Header from '../../Header';
import '../../../Styles/TelaAtendente.scss';

const TelaAtendente = () => {
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

    return(
        <>
            <Header logado={true} />
            <section id="secao__Atendente">
                <div id="visualizacao_fila">
                    <h2>Fila atual</h2>
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
                </div>
                <div id="visualizacao_pacientes">
                    <ul>
                        <li>a</li>
                        <li>b</li>
                        <li>c</li>
                        <li>d</li>
                        <li>e</li>
                    </ul>
                </div>
            </section>
        </>
    );
}

export default TelaAtendente;