package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.request.OcorrenciaRequestDTO;
import com.algaworks.algalog.api.dto.response.OcorrenciaResponseDTO;
import com.algaworks.algalog.api.mapper.OcorrenciaMapper;
import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.model.OcorrenciaModel;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{idEntrega}/ocorrencias")
public class OcorrenciaController {

    private RegistroOcorrenciaService registroOcorrenciaService;
    private BuscaEntregaService buscaEntregaService;
    private OcorrenciaMapper ocorrenciaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaResponseDTO registraOcorrencia(@PathVariable Long idEntrega,
                                                    @Valid @RequestBody OcorrenciaRequestDTO inputOcorrencia) {

        OcorrenciaModel ocorrenciaModel = registroOcorrenciaService
                .registrarOcorrencia(idEntrega, inputOcorrencia.getDescricao());

        return OcorrenciaResponseDTO.builder()
                .idEntrega(idEntrega)
                .idOcorrencia(ocorrenciaModel.getId())
                .descricao(ocorrenciaModel.getDescricao())
                .dataRegistro(LocalDateTime.now())
                .build();
    }

    /**
     * METODO COM ERRO PARA RETONAR TODAS OCORRENCIAS DE UM ID ENTREGRA
     *
     * @param idEntrega
     * @return List<OcorrenciaResponseDTO>
     */
    @GetMapping
    public List<OcorrenciaResponseDTO> buscaOcorrencias(@PathVariable Long idEntrega) {
        EntregaModel entregaModel = buscaEntregaService.buscaEntrega(idEntrega);

        List<OcorrenciaResponseDTO> ocorrencias = new ArrayList<>();
        for (OcorrenciaModel ocorrenciaModel : entregaModel.getOcorrencias()) {
            ocorrencias.add(OcorrenciaResponseDTO.builder()
                    .idEntrega(idEntrega)
                    .idOcorrencia(ocorrenciaModel.getId())
                    .descricao(ocorrenciaModel.getDescricao())
                    .dataRegistro(ocorrenciaModel.getDataRegistro())
                    .build());
        }

        return ocorrencias;
    }
}
