package com.algaworks.algalog.api.dto.request;

import com.algaworks.algalog.api.domain.request.ClienteModelRequest;
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
public class EntregaRequestDTO {

    @NotNull
    @Valid
    private ClienteModelRequest cliente;
}
