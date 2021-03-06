package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.ClienteModel;
import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.model.enums.StatusEntregaEnum;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private EntregaRepository entregaRepository;

    private CadastroClienteService cadastroClienteService;

    @Transactional
    public EntregaModel solicitarEntrega(EntregaModel entregaModel) {

        ClienteModel cliente = cadastroClienteService.buscarCliente(entregaModel.getCliente().getId());

        /**
         * Podemos criar aqui as regras de negocios
         * Como de tal hora a tal hora para entregar
         * Verificar se existe entregador disponivel
         */
        entregaModel.setCliente(cliente);
        entregaModel.setStatus(StatusEntregaEnum.PENDENTE);
        entregaModel.setDataPedido(LocalDateTime.now());

        return entregaRepository.save(entregaModel);
    }
}
