import { useState, useRef } from 'react';
import axios from 'axios';
import '../../Styles/Login.scss';

const TelaLogin = () => {
    const [dadosLogin, setDadosLogin] = useState({});
    const mensagemErro = useRef(null);
    
    const handleLogar = async (e) => {
        e.preventDefault();

        const res = await axios.post('http://localhost:8080/auth/paciente/login', dadosLogin).catch((erro) => {    
            if(erro.status == 400) {
                console.log('Usuário não encontrado');

                if(mensagemErro.current) {
                    mensagemErro.current.style.display = 'block';
                    mensagemErro.current.innerHTML = 'E-mail não encontrado';
                }

            } else if(erro.status == 401) {
                console.log('Informações inválidas');
    
                if(mensagemErro.current) {
                    mensagemErro.current.style.display = 'block';
                    mensagemErro.current.innerHTML = 'Usuário e/ou senha inválidos';
                }
            }
        });

        if(res) {
            localStorage.setItem('paciente', JSON.stringify(res));
                    
            if(mensagemErro.current) {
                mensagemErro.current.style.display = 'none';
            }
    
            console.log('Sessão armazenada: ' + localStorage.getItem('paciente'));

            window.location.href = '/paciente/consultas';
        }
    }

    const handleAlteracaoDados = (e) => {
        const { target } = e;
        const { name, value } = target;

        setDadosLogin({
            ...dadosLogin,
            [name]: value
        });

        // console.log(dadosLogin);
    }

    return(
        <main id="secao__Login">
            <form method="POST" onSubmit={handleLogar} id="caixa__Login">
                <h2>Logar-se</h2>

                <div className="caixa_Campos">
                    <input type="email" placeholder="Seu e-mail" name="email" id="email" onChange={handleAlteracaoDados} />
                    <input type="password" placeholder="Sua senha" name="senha" id="senha" onChange={handleAlteracaoDados} />
                </div>

                <div className="caixa_Opcoes">
                    <div className="checkbox">
                        <input type="checkbox" name="manterLogin" id="manterLogin" />
                        <label htmlFor="manterLogin">Manter-se conectado</label>
                    </div>
                    <a href="#">Esqueci minhas informações</a>
                </div>

                <div className="caixa_Botoes">
                    <a href="/registro/paciente">Cadastrar-se</a>
                    <input type="submit" value="Logar" />
                </div>

                <span className="erro" ref={mensagemErro}></span>
            </form>
        </main>
    );
}

export default TelaLogin;