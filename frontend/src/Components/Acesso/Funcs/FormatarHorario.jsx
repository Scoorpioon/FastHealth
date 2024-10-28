const horarioConsulta = (datahorario) => {
    const horarioEmLista = [];
    horarioEmLista.push(datahorario[3]), horarioEmLista.push(datahorario[4]);

    datahorario[4] == 0 ? horarioEmLista.push(0) : null;
    
    const horarioEmString = horarioEmLista.toString();
    let horarioFormatado = horarioEmString.replace(',', ':').replace(',', '');

    return horarioFormatado;
}

export default horarioConsulta;