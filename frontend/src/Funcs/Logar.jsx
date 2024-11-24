import axios from "axios";

const handleLogar = async (e, tipo, mensagem) => {
    e.preventDefault();

    let mensagemErro = mensagem;

    const res = await axios.post(`http://localhost:8080/auth/paciente/${tipo}`, dadosLogin).catch((erro) => {    
        if(erro.status == 400) {
            console.log('Usuário não encontrado');

            if(mensagemErro.current) {
                mensagemErro.current.style.display = 'block';
                mensagemErro.current.innerHTML = 'E-mail não encontrado';
            }

        } else if(erro.status == 401) {
            console.log('Informações inválidas');

            if(mensagemErro.current) {
                mensagemErro.current.style.display = 'block';
                mensagemErro.current.innerHTML = 'Usuário e/ou senha inválidos';
            }

        }
    });

    if(res) {
        localStorage.setItem(tipo, JSON.stringify(res));
                
        if(mensagemErro.current) {
            mensagemErro.current.style.display = 'none';
        }

        console.log('Sessão armazenada: ' + localStorage.getItem(tipo));
    }
}

export default handleLogar;