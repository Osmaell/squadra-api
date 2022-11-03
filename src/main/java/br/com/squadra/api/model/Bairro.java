package br.com.squadra.api.model;

import br.com.squadra.api.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
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
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_municipio")
    private Municipio municipio;

}