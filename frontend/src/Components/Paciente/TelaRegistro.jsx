import React, {useEffect, useState, useRef} from 'react';
import axios from 'axios';
import '../../Styles/Registro.scss';

const TelaRegistro = () => {
    const [dadosFormulario, setDadosFormulario] = useState({pcd: 0, nascimento: "1998-05-11"});
    const mensagemErro = useRef(null);
    
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const res = await axios.post('http://localhost:8080/pacientes/criar', dadosFormulario);

            if(res.status == 201) {
                localStorage.setItem('paciente', JSON.stringify({"data":{"nome":dadosFormulario.nome,"numCarteirinha":dadosFormulario.numCarteirinha}}));

                window.location.href = '/fila/2024-11-01';
                mensagemErro.current.style.display = 'none';
            }

        } catch(erro) {
            console.log('Ocorreu um erro ao tentar fazer a requisição: ' + erro);
            mensagemErro.current.style.display = 'block';
        }
    }

    const handleAlteracaoDados = (e) => {
        const { target } = e;
        const { name, value } = target;

        setDadosFormulario({
            ...dadosFormulario,
            [name]: value
        });
    }

    return(
        <form method="POST" onSubmit={handleSubmit} id="secao__Login">
            <section id="caixa__Login">
                <h2>Cadastro de paciente</h2>

                <div className="caixa_Campos">
                    <input type="text" placeholder="Seu nome" name="nome" id="nome" onChange={handleAlteracaoDados} />
                    <div className="caixa_Multiplas">
                        <input type="number" placeholder="Seu CPF" name="cpf" id="cpf" min="0" max="99999999999" onChange={handleAlteracaoDados} />
                        <input type="number" placeholder="Seu RG" name="rg" id="rg" min="0" max="999999999" onChange={handleAlteracaoDados} />
                    </div>
                    <input type="number" placeholder="Seu número de carteira SUS" name="numCarteirinha" id="numCarteirinha" min="0" max="9999999999999" onChange={handleAlteracaoDados} />
                    <input type="email" placeholder="Seu e-mail" name="email" id="email" onChange={handleAlteracaoDados} />
                    <input type="password" placeholder="Sua senha" name="senha" id="senha" onChange={handleAlteracaoDados} />
                </div>

                <div className="caixa_Opcoes">
                    <div className="checkbox">
                        <input type="checkbox" name="manterLogin" id="manterLogin" />
                        <label htmlFor="manterLogin">Manter-se conectado</label>
                    </div>
                </div>

                <div className="caixa_Botoes">
                    <input type="submit" value="Cadastrar-se" style={{color: 'white', backgroundColor: 'rgb(81, 0, 157)'}} />
                </div>
                <span className="erro" ref={mensagemErro}>Há informações já cadastradas em sistema. Por favor, revise os dados.</span>
            </section>
        </form>
    );
}

export default TelaRegistro;