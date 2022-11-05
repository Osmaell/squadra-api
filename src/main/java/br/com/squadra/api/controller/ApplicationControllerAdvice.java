package br.com.squadra.api.controller;

import br.com.squadra.api.exception.RegraNegocioException;
import br.com.squadra.api.utils.ApiErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(HttpStatus.BAD_REQUEST.value(), mensagemErro);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String entidade = ex.getTarget().getClass().toString()
                .replace("class br.com.squadra.api.dto.", "")
                .replace("DTO", "");

        String mensagem = String.format("Não foi possível consultar %s no banco de dados.", entidade);
        ApiErrors mensagemErro = new ApiErrors(HttpStatus.BAD_REQUEST.value(), mensagem);

        return handleExceptionInternal(ex, mensagemErro, headers, HttpStatus.BAD_REQUEST, request);
    }

}