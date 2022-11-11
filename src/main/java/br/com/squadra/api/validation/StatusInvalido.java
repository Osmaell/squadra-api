package br.com.squadra.api.validation;

import br.com.squadra.api.validation.constraint.StatusInvalidoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = StatusInvalidoValidator.class)
public @interface StatusInvalido {

    String message() default "Não foi possível incluir a Uf no banco de dados.<br>Motivo: O status só pode ser 1 ou 2.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}