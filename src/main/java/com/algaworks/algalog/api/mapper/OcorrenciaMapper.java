package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.dto.response.OcorrenciaResponseDTO;
import com.algaworks.algalog.domain.model.OcorrenciaModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class OcorrenciaMapper {

    private ModelMapper modelMapper;

    public OcorrenciaResponseDTO toModel(OcorrenciaModel ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaResponseDTO.class);
    }

    public List<OcorrenciaResponseDTO> toCollectionModel(List<OcorrenciaModel> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
