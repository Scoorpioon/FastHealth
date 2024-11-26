import { useEffect, useRef, useState } from "react";
import ListarHorarios from '../../Funcs/ListarHorarios';
import Header from "../Header";
import API from '../../api/config';
import axios from "axios";
import '../../Styles/Consultas.scss';

const CriacaoConsultas = () => {
    const [pacientes, setarPacientes] = useState();
    const [dados, setarDados] = useState({});
    const [erroReq, setarErroReq] = useState();
    const dataConsulta = useRef(null);
    const minutoConsulta = useRef(null);
    const horaConsulta = useRef(null);
    const mensagemErro = useRef(null);
    const mensagemSucesso = useRef(null);

    useEffect(() => {
        const requisitarPacientes = async () => {
            const res = await API.get('pacientes/buscarTodosPacientes').catch((erro) => {
                if(erro.status == 500) {
                    console.log('Não há pacientes cadastrados');
                }
            });

            if(res) {
                if(res.status == 200) {
                    setarPacientes(res.data);
                }
            }
        }
        
        requisitarPacientes();
    }, []);

    const listarPacientes = () => {
        if(pacientes) {
            return pacientes.map((paciente) => {
                return(
                    <option key={paciente.idPaciente} value={paciente.idPaciente}>{paciente.nome}</option>
                )
            });
        }
    }

    const pegarAlteracao = (e) => {
        const { target } = e;
        const { name, value } = target;

        // Explicação dos IFs: Se o campo alterado for hora ou minuto, guardaremos a informação no formato "2024-01-01T12:00". Se o campo for data, tanto ele quanto o campo dataHorario será atualizado. E precisamos definir a lógica do paciente manualmente também, pois precisamos enviar no formato de objeto e com a informação "idPaciente".

        if(name == 'horaConsulta' || name == 'minutoConsulta' || name == 'dataConsulta') {
            switch(name) {
                case 'horaConsulta':
                    setarDados({
                        ...dados,
                        dataHorarioConsulta: `${dataConsulta.current.value}T${value}:${minutoConsulta.current.value}`
                    });
                    break;
                case 'minutoConsulta':
                    setarDados({
                        ...dados,
                        dataHorarioConsulta: `${dataConsulta.current.value}T${value}:${minutoConsulta.current.value}`
                    });
                    break;
                case 'dataConsulta':
                    setarDados({
                        ...dados,
                        dataConsulta: value,
                        dataHorarioConsulta: `${dataConsulta.current.value}T${horaConsulta.current.value}:${minutoConsulta.current.value}`
                    });
                    break;
            }
        } else if(name == 'paciente') {
            setarDados({
                ...dados,
                paciente: {
                    idPaciente: value
                }
            });
        } else {
            setarDados({
                ...dados,
                [name]: value
            });
        }
    }

    const onSubmit = async (e) => {
        e.preventDefault();

        const estiloErro = mensagemErro.current.style;
        const estiloSucesso = mensagemSucesso.current.style;
        
        if(dados.dataConsulta && dados.dataHorarioConsulta && dados.tipoConsulta && dados.paciente) {
            API.post('/consultas/criar', dados)
            .then(() => {
                estiloSucesso.display = 'block';
                estiloErro.display = 'none';
            })
            .catch((erro) => {
                switch(erro.response.data) {
                    case 'consulta_existente':
                        mensagemErro.current.textContent = 'Já existe uma consulta marcada para o horário solicitado: '
                        estiloSucesso.display = 'none';
                        estiloErro.display = 'block';
                        break;
                }
            })

        } else {
            mensagemErro.current.textContent = 'Favor, preencha todas as informações para cadastrar a consulta.';
            estiloErro.display = 'block';
            estiloSucesso.display = 'none';

        }

    }

    return(
        <>
            <Header logado={true} tipoUsuario={'atendente'} />
            <section id="secao__CriacaoConsultas">
                <form action="POST" onSubmit={onSubmit}>
                    <div className="caixa_formulario caixa_horario">
                        <label htmlFor="dataConsulta">Data e horário da consulta</label>
                        <div className="horarios">
                            <input type="date" ref={dataConsulta} name="dataConsulta" id="dataConsulta" onChange={pegarAlteracao} />
                            <select name="horaConsulta" ref={horaConsulta} id="horaConsulta" onChange={pegarAlteracao}>
                                {ListarHorarios('hora')}
                            </select>
                            <span>:</span>
                            <select name="minutoConsulta" ref={minutoConsulta} id="minutoConsulta" onChange={pegarAlteracao}>
                                {ListarHorarios('minuto')}
                            </select>
                        </div>
                    </div>

                    <div className="caixa_formulario">
                        <label htmlFor="tipoConsulta">Tipo da consulta</label>
                        <select name="tipoConsulta" id="tipoConsulta" onChange={pegarAlteracao}>
                            <option disabled selected>Selecione um tipo de consulta...</option>
                            <option value="Clínico geral">Clínico geral</option>
                            <option value="Pediatria">Pediatria</option>
                            <option value="Cardiologista">Cardiologista</option>
                        </select>
                    </div>

                    <div className="caixa_formulario">
                        <label htmlFor="paciente">Paciente</label>
                        <select name="paciente" id="paciente" onChange={pegarAlteracao}>
                            <option disabled selected>Selecione um paciente...</option>
                            {listarPacientes()}
                        </select>
                    </div>

                    <input type="submit" value="Cadastrar consulta" />

                    <span className="erro" ref={mensagemErro}></span>
                    <span className="sucesso" ref={mensagemSucesso}>Consulta marcada com sucesso!</span>
                </form>
            </section>
        </>
    );
}

export default CriacaoConsultas;