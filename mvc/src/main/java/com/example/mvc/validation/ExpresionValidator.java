package com.example.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.example.mvc.annotation.ExpresionValidacion;

public class ExpresionValidator implements ConstraintValidator<ExpresionValidacion, Object> {
	
	private ExpressionParser expressionParser = new SpelExpressionParser();
	private Expression parsedExpression;
	
	@Override
	public void initialize(ExpresionValidacion annotation) {
		ConstraintValidator.super.initialize(annotation);
		parsedExpression = expressionParser.parseExpression(annotation.value());
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		StandardEvaluationContext spelContext = new StandardEvaluationContext(value);
		
		return (boolean) parsedExpression.getValue(spelContext);
	}

}
