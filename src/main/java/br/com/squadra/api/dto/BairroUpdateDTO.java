package br.com.squadra.api.dto;

import javax.validation.constraints.NotNull;

public class BairroUpdateDTO extends BairroDTO {

    @NotNull(message = "{msg.bairro_codigo_ausente}")
    @Override
    public Long getCodigoBairro() {
        return super.getCodigoBairro();
    }

}