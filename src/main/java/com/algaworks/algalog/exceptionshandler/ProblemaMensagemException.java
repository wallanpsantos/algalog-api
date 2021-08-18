package com.algaworks.algalog.exceptionshandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Inclua apenas propriedades não nulas no payload
public class ProblemaMensagemException {

    private Integer status;
    private LocalDateTime dataHora;
    private String mensagem;
    private List<CamposErroException> campos;
}
