package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.model.enums.StatusEntregaEnum;
import com.algaworks.algalog.domain.validations.GrupoValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "entrega")
public class EntregaModel {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    @ConvertGroup(to = GrupoValidation.idCliente.class)
    private ClienteModel cliente;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<OcorrenciaModel> ocorrencias = new ArrayList<>();

    @Valid
    @NotNull
    @Embedded // Separa os valores do destinario numa classe a parte
    private DestinatarioModel destinatario;

    @NotNull
    private BigDecimal taxa;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING) // Queremo amarzena a string que representa a constante da numeracao, no caso o nome
    private StatusEntregaEnum status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataFinalizacao;

    public OcorrenciaModel adicionarOcorrencia(String descricao) {
        OcorrenciaModel ocorrenciaModel = OcorrenciaModel.builder()
                .descricao(descricao)
                .dataRegistro(LocalDateTime.now())
                .entrega(this)
                .build();

        this.getOcorrencias().add(ocorrenciaModel);

        return ocorrenciaModel;
    }
}
