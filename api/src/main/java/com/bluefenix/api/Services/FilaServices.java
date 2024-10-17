package com.bluefenix.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Consulta;
import com.bluefenix.api.Models.Fila;
import com.bluefenix.api.Repositories.ConsultaRepository;
import com.bluefenix.api.Repositories.FilaRepository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import jakarta.transaction.Transactional;

@Service
public class FilaServices {
    
    @Autowired
    private FilaRepository repositorioFila;

    @Autowired
    private ConsultaRepository repositorioConsulta;

    @Transactional
    public Fila cadastrarFila(Fila dadosRecebidos) {
        // Por enquanto vai ser só isso memo
        dadosRecebidos.setIdFila(null);

        this.repositorioFila.save(dadosRecebidos);

        return dadosRecebidos;
    }

    @Transactional
    public List<Fila> findAll() {
        return this.repositorioFila.findAll();

    }

    @Transactional
    public Fila encontrarFilaPorData(LocalDate data) {
        Fila filaEncontrada = this.repositorioFila.findByDataFila(data);

        return filaEncontrada;
    }

/*     @Transactional
    public Consulta addConsultaToFila(Date data) {
        

    }
 */
    /* private Fila alterarPacienteAtual(long id) {

    } */

    public Fila removerPacienteDaFila(Long idConsulta, Long idFila) {
        Optional<Fila> filaBuscada = repositorioFila.findById(idFila);
        Optional<Consulta> consultaBuscada = repositorioConsulta.findById(idConsulta);

        if(filaBuscada.isPresent() && consultaBuscada.isPresent()) {
            Fila filaEncontrada = filaBuscada.get();
            Consulta consultaEncontrada = consultaBuscada.get();

            filaEncontrada.getConsultas().remove(consultaEncontrada);
            repositorioFila.save(filaEncontrada);

            return filaEncontrada;
        } else {
            throw new RuntimeException("Putz mano, não deu pra achar alguma das informações. Verifica de novo ae");
        }
    }
}