import React from 'react';
import Fila from './Fila';
import Header from '../../Header';

const TelaFila = () => {
    return(
        <>
            <Header logado={true} />
            <section id="secao__Fila">
                <Fila /> 
            </section>
        </>
    );
}

export default TelaFila;