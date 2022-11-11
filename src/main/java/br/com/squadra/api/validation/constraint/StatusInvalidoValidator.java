package br.com.squadra.api.validation.constraint;

import br.com.squadra.api.validation.StatusInvalido;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusInvalidoValidator implements ConstraintValidator<StatusInvalido, Integer> {

    @Override
    public boolean isValid(Integer status, ConstraintValidatorContext context) {
        return status == 1 || status == 2;
    }

}