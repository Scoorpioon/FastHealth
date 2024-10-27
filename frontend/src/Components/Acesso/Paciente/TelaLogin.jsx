import {useState} from 'react';
import axios from 'axios';
import '../../../Styles/Login.scss';

const TelaLogin = () => {
    const [dadosLogin, setDadosLogin] = useState({});
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await axios.post('http://localhost:8080/auth/paciente/login', dadosLogin);

        if(response.status == 401) {
            console.log('Informações inválidas');
        } else {
            localStorage.setItem('paciente', JSON.stringify(response));
        }

        console.log('Sessão armazenada: ' + localStorage.getItem('paciente'));
    }

    const handleAlteracaoDados = (e) => {
        const { target } = e;
        const { name, value } = target;

        setDadosLogin({
            ...dadosLogin,
            [name]: value
        });

        console.log(dadosLogin)
    }

    return(
        <main id="secao__Login">
            <form method="POST" onSubmit={handleSubmit} id="caixa__Login">
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
                    <a href="#">Cadastrar-se</a>
                    <input type="submit" value="Logar" />
                </div>
            </form>
        </main>
    );
}

export default TelaLogin;