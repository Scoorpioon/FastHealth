import Header from "./Header";
import '../Styles/Landing.scss';

const Landing = () => {
    return(
        <>
            <Header logado={false} />
            <main id="secao__Principal">
                <section id="principal">
                    <h2>O melhor jeito de aguardar pela sua consulta</h2>
                    <span>Visualize a fila diretamente de seu celular, no conforto de sua casa</span>

                    <div className="botoes">
                        <a href="/login/paciente">Sou paciente</a>
                        <a href="/login/atendente">Sou atendente</a>
                    </div>
                </section>
                <section id="paciente">
                    <div className="img"><img src="/ImagemPaciente.jpg" alt="Paciente sorrindo enquanto mexe no celular e aguarda por uma consulta" /></div>
                    <div className="texto">
                        <h2>Visualização da fila através de seu aparelho pessoal</h2>
                        <p>Nosso aplicativo oferece uma visualização em tempo real da fila de atendimento, permitindo que pacientes acompanhem exatamente quando serão chamados para consulta. Assim que um novo paciente entra ou é atendido, a fila se atualiza instantaneamente para todos os usuários, eliminando qualquer incerteza sobre a posição na fila. Essa tecnologia permite que o paciente acompanhe sua vez diretamente de seu celular, tablet, notebook ou computador, sendo notificado quando estiver próximo de ser chamado.
                        </p>
                        <p>
                            Essa visualização em tempo real traz diversas vantagens para você, como conveniência, tranquilidade, eficiência, transparência e satisfação.
                        </p>
                    </div>
                </section>
                <section id="atendente">
                    <div className="img"><img src="/ImagemAtendente.jpg" alt="Atendente sorrindo e mexendo no computador, gerenciando a fila de atendimento" /></div>
                    <div className="texto">
                        <h2>Gerenciamento simples e eficaz para atendentes</h2>
                        <p>Nosso sistema de gerenciamento de fila foi desenvolvido pensando na praticidade e eficiência para os atendentes das clínicas. Com uma interface intuitiva e ferramentas simplificadas, os atendentes conseguem visualizar e controlar a fila de atendimento em tempo real.
                        </p>
                        <p>Assim que um paciente é chamado, o atendente pode facilmente avançar para o próximo paciente com um simples clique, garantindo que a fila esteja sempre organizada e atualizada. Além disso, nosso sistema permite que o atendente veja o histórico de consultas passadas e adicione ou edite consultas, tudo em um único painel. Em caso de perda de conexão, há a opção de atualizar a fila manualmente, proporcionando segurança e continuidade no atendimento.</p>
                    </div>
                </section>
            </main>
            <footer>
                <div className="lateral_esquerda">
                    <h2>FastHealth</h2>
                </div>

                <span class="tm">&#169; Desenvolvido por Gabriel A.</span>
            </footer>
            
        </>
    );
}

export default Landing;