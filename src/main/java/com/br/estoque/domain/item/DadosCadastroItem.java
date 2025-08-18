package com.br.estoque.domain.item;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroItem (
		@NotBlank
		String SKU,
		@NotBlank
		String codigo,
		@NotBlank
		String descricao)
{
}
