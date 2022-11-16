package br.com.squadra.api.dto;

import javax.validation.constraints.NotNull;

public class MunicipioUpdateDTO extends MunicipioDTO {

    @NotNull(message = "{msg.municipio_codigo_ausente}")
    @Override
    public Long getCodigoMunicipio() {
        return super.getCodigoMunicipio();
    }
}