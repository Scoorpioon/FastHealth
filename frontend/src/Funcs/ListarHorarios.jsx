const ListarHorarios = (tipo) => {
    const valores = [];

    for(let c = 0; tipo === 'hora' ? c < 24 : c < 60; c++) {
        if(c < 10) {
            valores.push(<option key={c} value={`0${c}`}>0{c}</option>)
        } else {
            valores.push(<option key={c} value={c}>{c}</option>)
        }
    }

    return valores;
}

export default ListarHorarios