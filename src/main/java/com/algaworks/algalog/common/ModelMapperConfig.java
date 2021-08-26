package com.algaworks.algalog.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe para configurar os beans para a lib ModelMapper para o projeto
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Metodo responsavel por indicar que o bean que sera gerenciado pelo spring e disponibilizar para outras classes
     *
     * @return ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
