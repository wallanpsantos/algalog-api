package com.algaworks.algalog.api.dto.request;

import com.algaworks.algalog.api.domain.request.ClienteModelRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EntregaRequestDTO {

    private ClienteModelRequest cliente;
}
