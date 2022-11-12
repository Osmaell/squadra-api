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

    String message() default "{msg.uf_status_invalido}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}