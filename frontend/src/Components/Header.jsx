import React from 'react';
import '../Styles/Header.scss';

const Header = (props) => {
    const desconectar = () => {
        localStorage.clear();

        window.location.href = '/';
    }

    return(
        <header>
            <h1>FH Saúde</h1>

            {props.logado 
            ? 
            <span className="botao_desconectar" onClick={desconectar}>Sair da conta</span> 
            : 
            <div className="caixa_Botoes">
                <a href="/registro/paciente">Cadastro</a>
                <a href="/login/paciente">Entrar</a>
            </div>
            }
        </header>
    );
}

export default Header;