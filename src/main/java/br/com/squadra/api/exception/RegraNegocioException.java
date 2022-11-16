package br.com.squadra.api.exception;

import lombok.Getter;

public class RegraNegocioException extends RuntimeException {

    @Getter
    private String method;

    @Getter
    private String campo;

    public RegraNegocioException(String message) {
        super(message);
    }

    public RegraNegocioException(String message, String method, String campo) {
        super(message);
        this.method = method;
        this.campo = campo;
    }

}
