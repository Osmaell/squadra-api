package br.com.squadra.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "bairro")
@SequenceGenerator(name = Bairro.BAIRRO_SEQUENCE, sequenceName = Bairro.BAIRRO_SEQUENCE, allocationSize = 1)
public class Bairro {

    public static final String BAIRRO_SEQUENCE = "bairro_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BAIRRO_SEQUENCE)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @NotEmpty
    @Column(name = "nome", length = 200)
    private String nome;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_municipio")
    private Municipio municipio;

}