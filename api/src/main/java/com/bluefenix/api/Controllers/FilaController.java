package com.bluefenix.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluefenix.api.Services.FilaServices;
import com.bluefenix.api.Services.PacienteServices;

@RestController
@RequestMapping("/api/fila")
public class FilaController {
    
    @Autowired
    private FilaServices filaServicos;

    @Autowired
    private PacienteServices pacienteServicos;

    
}