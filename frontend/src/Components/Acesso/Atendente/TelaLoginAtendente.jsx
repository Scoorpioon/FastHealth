import Reac, {useState} from 'react';
import axios from 'axios';
import '../../../Styles/Login.scss';

const TelaLoginAtendente = () => {
    const [dadosLogin, setDadosLogin] = useState({});
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await axios.post('http://localhost:8080/auth/atendente/login', dadosLogin);

        if(response.status == 401) {
            console.log('Informações inválidas');
        } else if(response.status == 200) {
            console.log(`Logado com sucesso. Token gerado: ${response.data.token}`);
        }

        console.log(response);
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
        <form method="POST" onSubmit={handleSubmit} id="secao__Login">
            <section id="caixa__Login">
                <h2>Login de atendente</h2>

                <div className="caixa_Campos">
                    <input type="email" placeholder="Seu e-mail" name="email" id="email" onChange={handleAlteracaoDados} />
                    <input type="password" placeholder="Sua senha" name="senha" id="senha" onChange={handleAlteracaoDados} />
                </div>

{/*                 <div className="caixa_Opcoes">
                    <div className="checkbox">
                        <input type="checkbox" name="manterLogin" id="manterLogin" />
                        <label htmlFor="manterLogin">Manter-se conectado</label>
                    </div>
                </div> */}

                <div className="caixa_Botoes">
                    <input type="submit" value="Logar" />
                </div>
            </section>
        </form>
    );
}

export default TelaLoginAtendente;