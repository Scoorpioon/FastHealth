package com.bluefenix.api.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Hospital;
import com.bluefenix.api.Repositories.HospitalRepository;

@Service
public class HospitalServices {
    @Autowired
    HospitalRepository repositorioHospital;

    public Hospital cadastrarHospital(Hospital infos) {
        infos.setIdHospital(null);

        this.repositorioHospital.save(infos); // Realizar validação de CNPJ depois na seguinte API: https://publica.cnpj.ws/cnpj/{CNPJ}

        return infos;
    }

    public Optional<Hospital> encontrarPorID(String ID) {
        return this.repositorioHospital.findById(ID);
    }
}