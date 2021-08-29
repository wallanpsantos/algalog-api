package com.algaworks.algalog.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OcorrenciaResponseDTO {

    private Long idEntrega;

    private Long idOcorrencia;

    @NotBlank
    private String descricao;

    private LocalDateTime dataRegistro;
}
