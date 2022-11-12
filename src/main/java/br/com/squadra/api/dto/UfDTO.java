package br.com.squadra.api.dto;

import br.com.squadra.api.validation.StatusInvalido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UfDTO {

    @NotNull(message = "{campo.codigo-uf.obrigatorio}")
    private Long codigoUF;

    @Size(min = 1, max = 3, message = "{campo.sigla.tamanho.excedido}")
    @NotEmpty(message = "{campo.sigla.vazio}")
    @NotNull(message = "{campo.sigla.obrigatorio}")
    private String sigla;

    @NotEmpty(message = "{campo.nome.vazio}")
    @NotNull(message = "{campo.nome.obrigatorio}")
    private String nome;

    @StatusInvalido
    private Integer status;

}