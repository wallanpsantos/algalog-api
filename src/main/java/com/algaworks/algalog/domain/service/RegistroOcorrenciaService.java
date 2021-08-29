package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.model.OcorrenciaModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public OcorrenciaModel registrarOcorrencia(Long idEntrega, String descricao) {
        EntregaModel entregaModel = buscaEntregaService.buscaEntrega(idEntrega);

        return entregaModel.adicionarOcorrencia(descricao);
    }
}
