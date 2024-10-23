import React from 'react';
import '../../Styles/Login.scss';

const TelaRegistroAtendente = () => {
    return(
        <main id="secao__Login">
            <section id="caixa__Login">
                <h2>Cadastro de atendente</h2>

                <div className="caixa_Campos">
                    <input type="email" placeholder="Seu e-mail" name="email" id="email" />
                    <input type="password" placeholder="Sua senha" name="senha" id="senha" />
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
            </section>
        </main>
    );
}

export default TelaRegistroAtendente;