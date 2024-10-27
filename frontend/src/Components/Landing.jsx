import Header from "./Header";
import '../Styles/Landing.scss';

const Landing = () => {
    return(
        <>
            <Header logado={false} />
            <main id="secao__Principal">
                <div id="principal">
                    <h2>Um jeito melhor de aguardar pela sua consulta</h2>
                    <span>Experimente dançar com os pinguins de tanga</span>

                    <div className="botoes">
                        <a href="#">Sou paciente</a>
                        <a href="#">Sou atendente</a>
                    </div>
                </div>
            </main>
        </>
    );
}

export default Landing;