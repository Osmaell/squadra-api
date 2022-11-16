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
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDTO {

    private Long codigoMunicipio;

    @NotNull(message = "{msg.municipio_codigoUF_ausente}")
    private Long codigoUF;

    @Size(min = 3, message = "{msg.municipio.nome-tamanho-excedido}")
    @NotNull(message = "{msg.municipio_nome_ausente}")
    private String nome;

    @StatusInvalido(message = "{msg.municipio_status_invalido}")
    private Integer status;

    public boolean codigoMunicipioAusente() {
        return getCodigoMunicipio() == null;
    }

}
