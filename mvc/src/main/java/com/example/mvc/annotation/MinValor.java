package com.example.mvc.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.mvc.validation.MinValorValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = MinValorValidator.class)
public @interface MinValor {
	
	double value() default 0;
	
	String message() default "No cumple el valor mínimo";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
}
