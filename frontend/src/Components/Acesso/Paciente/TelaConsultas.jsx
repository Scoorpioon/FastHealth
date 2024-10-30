import Header from "../../Header";

const TelaConsultas = () => {
    return(
        <>
        <Header logado={true} tipoUsuario={'paciente'} />
        <section id="secao__ConsultasDoPaciente">
        </section>
        </>
    );
}

export default TelaConsultas;