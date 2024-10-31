const horarioConsulta = (datahorario) => {
    const horarioEmLista = [];
    let horarioFormatado;

    datahorario[3] < 10 ? horarioEmLista.push(0) : null;

    horarioEmLista.push(datahorario[3]), horarioEmLista.push(datahorario[4]);

    datahorario[4] == 0 ? horarioEmLista.push(0) : null;
    
    const horarioEmString = horarioEmLista.toString();

    if(datahorario[3] < 10) {
        horarioFormatado = horarioEmString
        .replace(',', '')
        .replace(',', ':')
        .replace(',', '');
    } else {
        horarioFormatado = horarioEmString
        .replace(',', ':')
        .replace(',', '');
    }

    return horarioFormatado;
}

export default horarioConsulta;