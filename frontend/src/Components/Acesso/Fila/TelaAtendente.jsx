import { useState, useEffect, useRef } from 'react';
import { Stomp } from '@stomp/stompjs';
import horarioConsulta from '../Funcs/FormatarHorario';
import SockJS from 'sockjs-client/dist/sockjs';
import Header from '../../Header';
import axios from 'axios';
import '../../../Styles/TelaAtendente.scss';

const TelaAtendente = () => {
    const [consultas, setConsultas] = useState();
    const [fila, setFila] = useState();
    const stompClient = useRef(null);
  
    useEffect(() => {
      const socket = new SockJS('http://localhost:8080/ws');
      stompClient.current = Stomp.over(socket);
  
      stompClient.current.connect({}, (frame) => {
        console.log('Conectado: ' + frame);

        stompClient.current.subscribe('/filaWS', (message) => {
          const updatedFila = JSON.parse(message.body);
          setFila(updatedFila);
        });
      }, (error) => {
        console.error('Erro ao conectar:', error);
      });
  
      return () => {
        if (stompClient.current) {
          stompClient.current.disconnect();
        }
      };
    }, []);
  
    const buscarFila = (data) => {
      if (stompClient.current && stompClient.current.connected) {
        stompClient.current.send('/app/retornarFilaPorData', {}, JSON.stringify({"dataFila": [2024, 8, 3]}));
      } else {
        console.error('Não deu pra estabalecer a conexão do STOMP por algum motivo. Se vira ai pra achar');
      }
    };
  
    // Função para remover uma consulta da fila
    const removerConsultaDaFila = (consultaId) => {
      if (stompClient.current && stompClient.current.connected) {
        stompClient.current.send('/app/removerConsulta', {}, JSON.stringify({ id: consultaId }));
      } else {
        console.error('Não deu pra estabalecer a conexão do STOMP por algum motivo. Se vira ai pra achar');
      }
    };
  
    // Função para inserir uma nova consulta na fila
    const inserirPacienteFila = (novaConsulta) => {
      if (stompClient.current && stompClient.current.connected) {
        stompClient.current.send('/app/inserirConsulta', {}, JSON.stringify({idFila: 1, idConsulta:2}));
      } else {
        console.error('Conexão STOMP não estabelecida.');
      }
    };

    useEffect(() => {
        const buscarConsultas = async () => {
            const res = await axios.get('http://localhost:8080/consultas/buscarConsultas/2024-08-03');
    
            setConsultas(res);
            console.log(res.data);
        }

        buscarConsultas();
    }, []);

    useEffect(() => {
        console.log(fila);
    }, [fila]);

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
                        <button onClick={buscarFila}>Atualizar fila</button>
                        <button onClick={() => {console.log('Paciente passado')}}>Passar paciente na fila</button>
                    </div>
                </div>
                <div id="visualizacao_pacientes">
                    <table className="tabela_consultas">
                        <thead>
                            <tr>
                                <th>Registro</th>
                                <th>Paciente</th>
                                <th>Tipo da consulta</th>
                                <th>Horário marcado</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                            consultas 

                            ?

                            consultas.data.map((consulta) => {
                                return <tr key={consulta.idConsulta}>
                                            <td>{consulta.idConsulta}</td>
                                            <td>{consulta.paciente.nome}</td>
                                            <td>{consulta.tipoConsulta}</td>
                                            <td>{horarioConsulta(consulta.dataHorarioConsulta)}</td>
                                            <td><button onClick={inserirPacienteFila}>Inserir na fila</button></td>
                                    </tr>})

                            :

                            <tr>
                                <td>
                                    <span>Carregando pacientes...</span>
                                </td>
                            </tr>
                            }
                        </tbody>
                    </table>
                </div>
            </section>
        </>
    );
}

export default TelaAtendente;