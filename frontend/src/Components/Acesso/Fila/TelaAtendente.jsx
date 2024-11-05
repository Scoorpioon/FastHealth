import { useState, useEffect, useRef, useContext } from 'react';
import { adicionarPacienteAtendido } from '../../../Context/Redux/slices/pacientesSlice';
import { useDispatch, useSelector } from 'react-redux';
import { Stomp } from '@stomp/stompjs';
import GerarSenhaAleatoria from '../Funcs/GerarSenhaAleatoria';
import horarioConsulta from '../Funcs/FormatarHorario';
import SockJS from 'sockjs-client/dist/sockjs';
import Header from '../../Header';
import axios from 'axios';
import '../../../Styles/TelaAtendente.scss';

const TelaAtendente = () => {
    const [consultas, setConsultas] = useState();
    const [senhas, setSenhas] = useState([]);
    const [fila, setFila] = useState();
    const pacientesAtendidos = useSelector(state => state.pacientes.lista);
    const botaoInserir = useRef(null);
    const stompClient = useRef(null);
    const dispatch = useDispatch();
    const date = new Date();
    const dataFila = [date.getFullYear(), date.getMonth() + 1, date.getUTCDate() - 1];
  
    useEffect(() => {
      const socket = new SockJS('http://localhost:8080/ws');
      stompClient.current = Stomp.over(socket);
  
      stompClient.current.connect({}, (frame) => {
        console.log('Conectado: ' + frame);

        stompClient.current.subscribe('/filaWS', (message) => {
          const updatedFila = JSON.parse(message.body);
          setFila(updatedFila);
        });

        buscarFila();

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
        stompClient.current.send('/app/retornarFilaPorData', {}, JSON.stringify({"dataFila": dataFila}));

      } else {
        console.error('Não deu pra estabalecer a conexão do STOMP por algum motivo. Se vira ai pra achar');
      }
    };
  
    // Função para remover uma consulta da fila
    const passarPacienteFila = () => {
      if(stompClient.current && stompClient.current.connected) {
        stompClient.current.send('/app/removerConsulta', {}, JSON.stringify({ idFila: fila.idFila, idConsulta: fila.consultas[0].idConsulta }));

      } else {
        console.error('Não deu pra estabalecer a conexão do STOMP por algum motivo. Se vira ai pra achar');
      }
    };
  
    // Função para inserir uma nova consulta na fila
    const inserirPacienteFila = (e) => {
        setSenhas(...senhas, [GerarSenhaAleatoria()]);

        console.log(e.target);
        e.target.disabled = 'true';

        for(let c = 0; c < fila.consultas.length; c++) {
            if(fila.consultas[c].idConsulta == e.target.value) {
                console.log('Esse id já está inserido na fila.');

                e.target.disabled = true;
                e.target.classList.add('desabilitado');
                
                return false;
            }
        }

        if (stompClient.current && stompClient.current.connected) {
          stompClient.current.send('/app/inserirConsulta', {}, JSON.stringify({idFila: fila.idFila, idConsulta: e.target.value}));

        } else {
          console.error('Conexão STOMP não estabelecida.');
        }
        
    };

    useEffect(() => {
        const buscarConsultas = async () => {
          const data = `${dataFila[0]}-${dataFila[1]}-${dataFila[2] < 10 ? '0' + dataFila[2] : dataFila[2]}`;
          const res = await axios.get(`http://localhost:8080/consultas/buscarConsultas/${data}`);
  
          setConsultas(res);
          console.log(res.data);
        }

        buscarConsultas();

        console.log('Data: ' + dataFila);
    }, []);

    const pacientesDoDia = () => {
      if(consultas) {
        return consultas.data.map((consulta) => {
          return(
          <tr key={consulta.idConsulta}>
            <td>{consulta.idConsulta}</td>
            <td>{consulta.paciente.nome}</td>
            <td>{consulta.tipoConsulta}</td>
            <td>{horarioConsulta(consulta.dataHorarioConsulta)}</td>
            <td><button 
            onClick={inserirPacienteFila}
            value={consulta.idConsulta}
            >Inserir na fila</button></td>
          </tr>)})
      } else {
        return(
          <tr>
            <td>
                <span>Carregando pacientes...</span>
            </td>
          </tr>
        )
      }
    }

    const inserirPacientes = () => {
        if(fila) {
            return fila.consultas.map((consulta, pos) => {
              if(pos == 0) {
                return <li className="paciente_atual" key={consulta.idConsulta}><strong>{consulta.paciente.nome}</strong> - Atual</li>
              } else {
                return <li key={consulta.idConsulta}>{consulta.paciente.nome}</li>
              }
            })
        } else {
            return <span>Carregando...</span>
        }
    }

    return(
        <>
            <Header logado={true} tipoUsuario={'atendente'} />
            <section id="secao__Atendente">
                <div id="visualizacao_fila">
                    <h2>Fila de hoje</h2>
                    <ul className="nome_pacientes">
                    {inserirPacientes()}
                    </ul>

                    <div className="caixa__Botoes">
                        <button onClick={buscarFila}>Atualizar fila</button>
                        <button onClick={passarPacienteFila}>Passar paciente na fila</button>
                    </div>
                </div>
                <div id="visualizacao_pacientes">
                  <h2>Pacientes para o dia de hoje: {dataFila[2]}/{dataFila[1]}/{dataFila[0]}</h2>
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
                            {pacientesDoDia()}
                        </tbody>
                    </table>
                </div>
            </section>
        </>
    );
}

export default TelaAtendente;