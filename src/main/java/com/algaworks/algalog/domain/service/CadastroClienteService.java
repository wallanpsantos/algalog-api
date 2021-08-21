package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exceptions.NegocioException;
import com.algaworks.algalog.domain.model.ClienteModel;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroClienteService {

    private ClienteRepository clienteRepository;

    public ClienteModel buscarCliente(Long idCliente) {
        return clienteRepository.findById(idCliente).orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
    }

    @Transactional
    public ClienteModel adicionarCliente(ClienteModel cliente) {

        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(existe -> !existe.equals(cliente));

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
