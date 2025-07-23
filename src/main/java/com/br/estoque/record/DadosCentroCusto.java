package com.br.estoque.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCentroCusto (
		String id, 
		@NotBlank
		String nome, 
		@NotBlank
		String apelido, 
		@NotNull
		boolean ativo) {

}
