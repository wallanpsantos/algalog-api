package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping("/solicitar")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitarEntrega(@Valid @RequestBody EntregaModel entrega) {
        return solicitacaoEntregaService.solicitarEntrega(entrega);
    }

    @GetMapping
    public List<EntregaModel> listaEntregas() {
        return entregaRepository.findAll();
    }

    @GetMapping("/{idEntrega}")
    public ResponseEntity<EntregaModel> buscaEntrega(@PathVariable Long idEntrega) {
        return entregaRepository.findById(idEntrega).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
