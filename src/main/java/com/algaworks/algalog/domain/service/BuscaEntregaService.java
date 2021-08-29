package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    public EntregaModel buscaEntrega(Long idEntrega) {
        return entregaRepository.findById(idEntrega)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada!"));
    }
}
