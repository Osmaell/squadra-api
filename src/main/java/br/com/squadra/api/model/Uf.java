package br.com.squadra.api.model;

import br.com.squadra.api.validation.StatusInvalido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "uf")
@SequenceGenerator(name = Uf.UF_SEQUENCE, sequenceName = Uf.UF_SEQUENCE, allocationSize = 1)
public class Uf {

    public final static String UF_SEQUENCE = "uf_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = UF_SEQUENCE)
    @Column(name = "codigo")
    private Long codigo;

    @Size(min = 1, max = 3, message = "{campo.sigla.tamanho.excedido}")
    @NotNull(message = "{msg.uf_sigla_ausente}")
    @Column(name = "sigla", length = 3)
    private String sigla;

    @Size(min = 3, message = "{campo.nome.tamanho-invalido}")
    @NotNull(message = "{msg.uf_nome_ausente}")
    @Column(name = "nome", length = 60)
    private String nome;

    @StatusInvalido
    @NotNull(message = "{msg.uf_status_ausente}")
    @Column(name = "status")
    private Integer status;

}