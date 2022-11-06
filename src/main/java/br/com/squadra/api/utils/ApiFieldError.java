package br.com.squadra.api.utils;


import lombok.Getter;

public class ApiFieldError extends ApiError {

    @Getter
    private String nomeDoCampo;

    public ApiFieldError(Integer status, String mensagem) {
        super(status, mensagem);
    }

    public ApiFieldError(Integer status, String mensagem, String nomeDoCampo) {
        super(status, mensagem);
        this.nomeDoCampo = nomeDoCampo;
    }
}
