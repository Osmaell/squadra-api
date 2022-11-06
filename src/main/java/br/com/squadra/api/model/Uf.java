package br.com.squadra.api.model;

import br.com.squadra.api.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotEmpty(message = "{msg.uf_sigla_vazia}")
    @NotNull(message = "{msg.uf_sigla_ausente}")
    @Column(name = "sigla", length = 3)
    private String sigla;

    @NotEmpty(message = "{msg.uf_nome_vazio}")
    @NotNull(message = "{msg.uf_nome_ausente}")
    @Column(name = "nome", length = 60)
    private String nome;

    @NotNull(message = "{msg.uf_status_ausente}")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

}