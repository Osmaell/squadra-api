package br.com.squadra.api.utils;

import lombok.Getter;

public class ApiError {

    @Getter
    private String mensagem;

    @Getter
    private Integer status;

    public ApiError(Integer status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

}