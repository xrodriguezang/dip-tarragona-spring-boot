package com.example.mvc.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.mvc.annotation.MinValor;

public class MinValorValidator implements ConstraintValidator<MinValor, BigDecimal> {
	
	Double valorMin;
	
	
	@Override
	public void initialize(MinValor annotation) {
		ConstraintValidator.super.initialize(annotation);
		valorMin = annotation.value();
	}

	@Override
	public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
		Double valor = value.doubleValue();
		
		if(valor.compareTo(valorMin) >= 0 ) {
			return true;
		}
		
		return false;
	}
	
	
	
}
