package br.com.squadra.api.controller;

import br.com.squadra.api.exception.RegraNegocioException;
import br.com.squadra.api.utils.ApiError;
import br.com.squadra.api.utils.ApiFieldError;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRegraNegocioException(RegraNegocioException ex) {
        String mensagemErro = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        return new ApiError(HttpStatus.BAD_REQUEST.value(), mensagemErro);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String entidade = ex.getTarget().getClass().toString()
                .replace("class br.com.squadra.api.dto.", "")
                .replace("DTO", "");

        String mensagem = String.format("Não foi possível consultar %s no banco de dados.", entidade);
        ApiError mensagemErro = new ApiError(HttpStatus.BAD_REQUEST.value(), mensagem);

        return handleExceptionInternal(ex, mensagemErro, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ApiError> erros = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String sigla = fieldError.getField();
            erros.add(new ApiFieldError(HttpStatus.BAD_REQUEST.value(), mensagem, sigla));
        });

        return handleExceptionInternal(ex, erros.stream().findFirst(), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String campo = null;
        Throwable cause = ex.getCause();

        if (cause instanceof MismatchedInputException) {

            MismatchedInputException mie = (MismatchedInputException) cause;

            if (mie.getPath() != null && mie.getPath().size() > 0) {
                campo = mie.getPath().get(0).getFieldName();
            }

        }

        String mensagem = messageSource.getMessage("msg.campo_invalido", null, LocaleContextHolder.getLocale());
        String mensagemErro = String.format("%s %s esta recebendo um tipo de dado inválido.", mensagem, campo);

        ApiFieldError error = new ApiFieldError(HttpStatus.BAD_REQUEST.value(), mensagemErro, campo);

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }
}