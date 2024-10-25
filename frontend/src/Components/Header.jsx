import React from 'react';
import '../Styles/Header.scss';

const Header = (props) => {
    return(
        <header>
            <h1>FH Saúde</h1>

            {props.logado 
            ? 
            <span className="botao_desconectar">Sair</span> 
            : 
            <div className="caixa_Botoes">
                <a href="#">Cadastro</a>
                <a href="#">Entrar</a>
            </div>
            }
        </header>
    );
}


export default Header;