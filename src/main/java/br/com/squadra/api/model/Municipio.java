package br.com.squadra.api.model;

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
@Table(name = "municipio")
@SequenceGenerator(name = Municipio.MUNICIPIO_SEQUENCE, sequenceName = Municipio.MUNICIPIO_SEQUENCE, allocationSize = 1)
public class Municipio {

    public static final String MUNICIPIO_SEQUENCE = "municipio_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = MUNICIPIO_SEQUENCE)
    @Column(name = "codigo")
    private Long codigoMunicipio;

    @NotEmpty
    @NotNull
    @Column(name = "nome", length = 200)
    private String nome;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_uf")
    private Uf uf;

}