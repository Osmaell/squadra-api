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
@Table(name = "endereco")
@SequenceGenerator(name = Endereco.ENDERECO_SEQUENCE, sequenceName = Endereco.ENDERECO_SEQUENCE, allocationSize = 1)
public class Endereco {

    public static final String ENDERECO_SEQUENCE = "endereco_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ENDERECO_SEQUENCE)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @NotEmpty
    @Column(name = "nome_rua", length = 200)
    private String nomeRua;

    @NotNull
    @NotEmpty
    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "complemento", length = 20)
    private String complemento;

    @NotNull
    @NotEmpty
    @Column(name = "cep", length = 10)
    private String cep;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_bairro")
    private Bairro bairro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_pessoa")
    private Pessoa pessoa;

}