package com.br.estoque.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCentroCusto (
		@NotBlank
		String nome, 
		@NotBlank
		String apelido, 
		@NotNull
		boolean ativo) {

}
