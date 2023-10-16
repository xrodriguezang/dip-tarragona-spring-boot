package com.example.mvc.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.mvc.validation.ExpresionValidator;

@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(ExpresionValidaciones.class)
@Constraint(validatedBy = ExpresionValidator.class)
public @interface ExpresionValidacion {
	
	String value();
	
	String message() default "Validación incorrecta";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
}
