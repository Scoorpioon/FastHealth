import { useEffect, useRef, useState } from "react";
import Header from "../../Header";
import API from '../../../api/config';
import ListarHorarios from '../Funcs/ListarHorarios';
import axios from "axios";
import '../../../Styles/Consultas.scss';

const CriacaoConsultas = () => {
    const [pacientes, setarPacientes] = useState();
    const [dados, setarDados] = useState({});
    const dataConsulta = useRef(null);
    const minutoConsulta = useRef(null);
    const horaConsulta = useRef(null);
    const erro = useRef(null);

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

        if(name == 'horaConsulta' || name == 'minutoConsulta') {
            if(name == 'horaConsulta') {
                setarDados({
                    ...dados,
                    dataHorarioConsulta: `${dataConsulta.current.value}T${value}:${minutoConsulta.current.value}`
                });   
            } else {
                setarDados({
                    ...dados,
                    dataHorarioConsulta: `${dataConsulta.current.value}T${horaConsulta.current.value}:${value}`
                });   
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

    const onSubmit = (e) => {
        e.preventDefault();
        
        if(dados.dataConsulta && dados.dataHorarioConsulta && dados.tipoConsulta && dados.paciente) {
            API.post('/consultas/criar', dados);

            erro.current.style.display = 'none';
        } else {
            console.log('Algum dado não foi preenchido, a requisição não foi feita.');

            erro.current.style.display = 'block';
        }
    }

    return(
        <>
            <Header logado={true} />
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

                    <span className="erro" ref={erro}>Favor, preencha todas as informações para cadastrar a consulta.</span>
                </form>
            </section>
        </>
    );
}

export default CriacaoConsultas;