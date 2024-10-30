import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    lista: []
}

const pacientesSlice = createSlice({
    name: 'pacientes',
    initialState,
    reducers: {
        adicionarPacienteAtendido: (state, action) => {
            state.lista.push(action.payload); 
        }
    }
});

export const { adicionarPacienteAtendido } = pacientesSlice.actions;

export default pacientesSlice.reducer;