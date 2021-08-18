package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.model.enums.StatusEntregaEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "entrega")
public class EntregaModel {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Muita entregas para um cliente (cliente_id - chave estrangeira)
//    @JoinColumn(name = "codigo_cliente") // Especificando a coluna para relacionar. Contrario sera padrao cliente_id
    private ClienteModel cliente;

    @Embedded // Separa os valores do destinario numa classe a parte
    private DestinationModel destination;

    private BigDecimal taxa;

    @Enumerated(EnumType.STRING) // Queremo amarzena a string que representa a constante da numeracao, no caso o nome
    private StatusEntregaEnum status;

    private LocalDateTime dataPedido;
    private LocalDateTime dataFinalizacao;
}
