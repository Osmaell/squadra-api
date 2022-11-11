package br.com.squadra.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pessoa")
@SequenceGenerator(name = Pessoa.PESSOA_SEQUENCE, sequenceName = Pessoa.PESSOA_SEQUENCE, allocationSize = 1)
public class Pessoa {

    public static final String PESSOA_SEQUENCE = "pessoa_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PESSOA_SEQUENCE)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @NotEmpty
    @Column(name = "nome", length = 200)
    private String nome;

    @NotNull
    @NotEmpty
    @Column(name = "sobrenome", length = 200)
    private String sobrenome;

    @NotNull
    @Column(name = "idade")
    private int idade;

    @NotNull
    @NotEmpty
    @Column(name = "login", length = 50)
    private String login;

    @NotNull
    @NotEmpty
    @Column(name = "senha", length = 50)
    private String senha;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

}