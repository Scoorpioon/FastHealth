import Header from "./Header";
import '../Styles/Landing.scss';

const Landing = () => {
    return(
        <>
            <Header logado={false} />
            <main id="secao__Principal">
                <div id="principal">
                    <h2>O melhor jeito de aguardar pela sua consulta</h2>
                    <span>Visualize a fila diretamente de seu celular, no conforto de sua casa</span>

                    <div className="botoes">
                        <a href="#">Sou paciente</a>
                        <a href="/login/atendente">Sou atendente</a>
                    </div>
                </div>
            </main>
        </>
    );
}

export default Landing;