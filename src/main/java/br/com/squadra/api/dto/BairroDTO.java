package br.com.squadra.api.dto;

import br.com.squadra.api.validation.StatusInvalido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BairroDTO {

    private Long codigoBairro;

    @NotNull(message = "{campo.codigo-municipio.obrigatorio}")
    private Long codigoMunicipio;

    @NotNull(message = "{campo.nome.obrigatorio}")
    @Size(min = 3, message = "{msg.campo.nome.tamanho.invalido}")
    private String nome;

    @StatusInvalido(message = "{msg.bairro_status_invalido}")
    private Integer status;

}