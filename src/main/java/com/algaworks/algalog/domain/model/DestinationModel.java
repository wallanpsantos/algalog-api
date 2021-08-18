package com.algaworks.algalog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable // Indica que a classe pode se usada em alguma entidade relacional
public class DestinationModel {

    @Column(name = "destinatario_nome")
    private String nome;

    @Column(name = "destinatario_logradouro")
    private String logradouro;

    @Column(name = "destinatario_numero")
    private String numero;

    @Column(name = "destinatario_complemento")
    private String complemento;

    @Column(name = "destinatario_bairro")
    private String bairro;
}
