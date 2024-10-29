import Reac, {useState} from 'react';
import axios from 'axios';
import '../../../Styles/Login.scss';

const TelaLoginAtendente = () => {
    const [dadosLogin, setDadosLogin] = useState({});
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        const res = await axios.post('http://localhost:8080/auth/atendente/login', dadosLogin);

        if(response.status == 401) {
            console.log('Informações inválidas');
        } else if(response.status == 200) {
            console.log(`Logado com sucesso. Token gerado: ${response.data.token}`);
        }

        if(res) {
            window.location.href = '/painelDoAtendente';
        }
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

                <div className="caixa_Opcoes">
                    <div className="checkbox">
                        <span className="aviso_atendente">Em caso de esquecimento de credenciais, contate a equipe responsável.</span>
                    </div>
                </div>

                <div className="caixa_Botoes">
                    <input type="submit" value="Logar" style={{color: 'white', backgroundColor: 'purple'}} />
                </div>
            </section>
        </form>
    );
}

export default TelaLoginAtendente;