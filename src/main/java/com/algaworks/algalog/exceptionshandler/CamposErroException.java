package com.algaworks.algalog.exceptionshandler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CamposErroException {

    private String nome;
    private String mensagem;
}
