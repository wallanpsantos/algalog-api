package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.dto.response.EntregaResponseDTO;
import com.algaworks.algalog.domain.model.EntregaModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe reponsavel por fazer a conversão de um dominio de entrega para payload de saida
 */
@AllArgsConstructor
@Component
public class EntregaMapper {

    private ModelMapper modelMapper;

    /**
     * Metodo responsavel por converter modelo de entrega para response dto de entrega
     *
     * @param entregaModel
     * @return EntregaResponseDTO
     */
    public EntregaResponseDTO toModel(EntregaModel entregaModel) {

        return modelMapper.map(entregaModel, EntregaResponseDTO.class);
    }

    /**
     * Metodo responsavel por converter modelo de entrega em lista de entregas
     *
     * @param entregases
     * @return List<EntregaResponseDTO>
     */
    public List<EntregaResponseDTO> toCollectionModel(List<EntregaModel> entregases) {
        return entregases.stream().map(this::toModel).collect(Collectors.toList());
    }
}
