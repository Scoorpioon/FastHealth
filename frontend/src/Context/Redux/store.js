import { configureStore } from "@reduxjs/toolkit";
import pacientesSlicer from './slices/pacientesSlice';

const store = configureStore({
    reducer: {
        pacientes: pacientesSlicer
    }
});

export default store;