package com.algaworks.algalog.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DestinatarioResponseDTO {

    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
}
