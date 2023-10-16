package com.example.mvc.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.mvc.annotation.ExpresionValidacion;
import com.example.mvc.annotation.ExpresionValidaciones;
import com.example.mvc.annotation.MinValor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ExpresionValidaciones(value = {
	@ExpresionValidacion(value = "!(#this.nombre == null)", message = "El nombre no puede ser nulo"),
	@ExpresionValidacion(value = "!(#this.email == null)", message = "El email no puede ser nulo")
})
public class Empleado {
//	@NotNull
	private UUID id;
	private String nombre;
	private String email;
	@MinValor(value = 10000, message = "No me pagas lo suficiente")
	private BigDecimal salario;
}
