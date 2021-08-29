package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.response.DestinatarioResponseDTO;
import com.algaworks.algalog.api.dto.response.EntregaResponseDTO;
import com.algaworks.algalog.domain.model.DestinatarioModel;
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
    public EntregaModel solicitarEntrega(@Valid @RequestBody EntregaModel entregaModel) {
        return solicitacaoEntregaService.solicitarEntrega(entregaModel);
    }

    @GetMapping
    public List<EntregaModel> listaEntregas() {
        return entregaRepository.findAll();
    }

    @GetMapping("/{idEntrega}")
    public ResponseEntity<EntregaResponseDTO> buscaEntrega(@PathVariable Long idEntrega) {
        return entregaRepository.findById(idEntrega)
                .map(entregaModel -> ResponseEntity.ok(
                        EntregaResponseDTO.builder()
                                .id(entregaModel.getId())
                                .nomeCliente(entregaModel.getCliente().getNome())
                                .destinatario(getDestinatarioModel(new DestinatarioModel(), idEntrega))
                                .taxa(entregaModel.getTaxa())
                                .dataPedido(entregaModel.getDataPedido())
                                .dataFinalizacao(entregaModel.getDataFinalizacao())
                                .build()
                )).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Metodo responsavel por retornar a tranferencia de modelo do objeto destinatario
     *
     * @return DestinatarioResponseDTO
     */
    private DestinatarioResponseDTO getDestinatarioModel(DestinatarioModel destinatarioModel, Long idEntrega) {
        destinatarioModel = entregaRepository.findById(idEntrega).get().getDestinatario();
        return DestinatarioResponseDTO.builder()
                .nome(destinatarioModel.getNome())
                .logradouro(destinatarioModel.getLogradouro())
                .numero(destinatarioModel.getNumero())
                .bairro(destinatarioModel.getBairro())
                .build();
    }

}
