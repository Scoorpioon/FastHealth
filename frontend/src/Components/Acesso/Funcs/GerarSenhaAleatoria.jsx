const GerarSenhaAleatoria = () => {
    const alfabeto = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const numeros = '0123456789';
    let senha = [];
    let random;

    for(let c = 0; c <= 3; c++) {
        random = Math.floor(Math.random() * 10);

        if(c == 0) {
            senha.push(alfabeto[random]);
        } else {
            senha.push(numeros[random]);
        }
    }

    return senha;
}

export default GerarSenhaAleatoria;