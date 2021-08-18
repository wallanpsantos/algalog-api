package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.ClienteModel;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CadastroClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    //@Autowired
    private ClienteRepository clienteRepository;

    private CadastroClienteService cadastroClienteService;

    @GetMapping
    public List<ClienteModel> getClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/nome/{nomeCliente}")
    public List<ClienteModel> getNomeCliente(@PathVariable String nomeCliente) {
        return clienteRepository.findByNomeContaining(nomeCliente);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteModel> getIdCliente(@PathVariable Long idCliente) {
        return clienteRepository.findById(idCliente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ClienteModel> adicionarCliente(@Valid @RequestBody ClienteModel cliente) {
        if (Objects.nonNull(cliente)) {
            return new ResponseEntity<>(cadastroClienteService.adicionarCliente(cliente), HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/atualizar/{idCliente}")
    public ResponseEntity<ClienteModel> atualizarCliente(
            @PathVariable Long idCliente, @Valid @RequestBody ClienteModel cliente) {

        if (clienteRepository.existsById(idCliente)) {
            cliente.setId(idCliente);
            cliente = cadastroClienteService.adicionarCliente(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cadastroClienteService.adicionarCliente(cliente), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{idCliente}")
    public ResponseEntity<String> removeCliente(@PathVariable Long idCliente) {
        if (clienteRepository.existsById(idCliente)) {
            cadastroClienteService.deletarCliente(idCliente);
            return ResponseEntity.ok().body("Cliente removido com sucesso");
        }
        return ResponseEntity.badRequest().build();
    }
}
