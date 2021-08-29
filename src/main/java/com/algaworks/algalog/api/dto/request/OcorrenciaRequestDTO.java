package com.algaworks.algalog.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OcorrenciaRequestDTO {

    @NotNull
    @Valid
    private String descricao;
}
