package br.com.squadra.api.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private String mensagem;

    @Getter
    private Integer status;

    public ApiErrors(Integer status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

}