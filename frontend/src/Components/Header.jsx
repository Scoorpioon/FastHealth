import React from 'react';
import '../Styles/Header.scss';

const Header = ({logado, tipoUsuario}) => {
    const desconectar = () => {
        localStorage.clear();
        window.location.href = '/';
    }

    const logicaHeader = () => {
        if(logado) {
            if(tipoUsuario === 'atendente') {
                return(
                    <div className="caixa_Opcoes">
                        <a href="/painelDoAtendente">Gerenciamento de fila</a>
                        <a href="/consulta/criar">Criar consulta</a>
                        <span className="botao_desconectar" onClick={desconectar}>Sair da conta</span>
                    </div>
                )
            } else if(tipoUsuario === 'paciente') {
                return(
                    <div className="caixa_Opcoes">
                        <a href="/fila/2024-11-01">Visualizar fila</a>
                        <span className="botao_desconectar" onClick={desconectar}>Sair da conta</span>
                    </div>
                )
            }
        } else {
            return(
            <div className="caixa_Botoes">
                <a href="/registro/paciente">Cadastro</a>
                <a href="/login/paciente">Entrar</a>
            </div>
            )
        }
    }

    return(
        <header>
            <a href="/">
                <h1>FH Saúde</h1>
            </a>
            {logicaHeader()}
        </header>
    );
}

export default Header;