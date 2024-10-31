const abreviarNome = (nome) => {
    const nomeEmLista = nome.split(" ");
    const nomeFormatado = [];
    
    nomeFormatado.push(nomeEmLista[0]);

    if(nomeEmLista.length > 1) {
        for(let c = 1; c < nomeEmLista.length; c++) {
            nomeFormatado.push(` ${nomeEmLista[c][0]}. `);
        }
    }

    return nomeFormatado;
}

export default abreviarNome;