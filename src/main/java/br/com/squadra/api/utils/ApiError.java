package br.com.squadra.api.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ApiError {

    @Getter
    @Setter
    private String mensagem;

    @Getter
    @Setter
    private Integer status;

    public ApiError(Integer status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

}