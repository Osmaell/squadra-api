package br.com.squadra.api.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    private HttpStatus status;

    public ApiErrors(HttpStatus status, String mensagemErro) {
        this.status = status;
        this.errors = Arrays.asList(mensagemErro);
    }

    public ApiErrors(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }



}
