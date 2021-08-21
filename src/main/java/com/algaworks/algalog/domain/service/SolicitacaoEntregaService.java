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
    public EntregaModel solicitarEntrega(EntregaModel entrega) {

        ClienteModel cliente = cadastroClienteService.buscarCliente(entrega.getCliente().getId());

        /**
         * Podemos criar aqui as regras de negocios
         * Como de tal hora a tal hora para entregar
         * Verificar se existe entregador disponivel
         */
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntregaEnum.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());

        return entregaRepository.save(entrega);
    }
}
