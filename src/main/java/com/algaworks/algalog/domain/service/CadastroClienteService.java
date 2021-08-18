package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exceptions.NegocioException;
import com.algaworks.algalog.domain.model.ClienteModel;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteModel adicionarCliente(ClienteModel cliente) {

        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(existe -> !existe  .equals(cliente));

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");

        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deletarCliente(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }
}
