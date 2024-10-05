package com.bluefenix.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Repositories.FilaRepository;

@Service
public class FilaServices {
    
    @Autowired
    private FilaRepository filaRepositorio;

    
}