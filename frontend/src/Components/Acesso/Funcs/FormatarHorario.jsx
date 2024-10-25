const horarioConsulta = (datahorario) => {
    const horarioEmLista = [];
    horarioEmLista.push(datahorario[3]), horarioEmLista.push(datahorario[4]);
    
    const horarioEmString = horarioEmLista.toString();
    const horarioFormatado = horarioEmString.replace(',', ':');

    return horarioFormatado;
}

export default horarioConsulta;