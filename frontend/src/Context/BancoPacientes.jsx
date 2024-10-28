import { createContext, useState } from 'react';

const PacientesPassados = createContext();

export const BancoPacientes = ({children}) => {
    const [pacientes, setPacientes] = useState([]);

    return(
        <PacientesPassados.Provider value={{pacientes, setPacientes}}>
            {children}
        </PacientesPassados.Provider>
    );
}

export default PacientesPassados;