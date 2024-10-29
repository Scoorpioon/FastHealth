import { createContext, useState } from 'react';

export const BancoPacientes = createContext();

export const BancoPacientesProvider = ({children}) => {
    const [pacientes, setPacientes] = useState([]);

    return(
        <BancoPacientes.Provider value={{pacientes, setPacientes}}>
            {children}
        </BancoPacientes.Provider>
    );
}