package com.br.estoque.domain.item;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoItem (
		@NotNull
		Long id,
		String SKU,
		String codigo,
		String descricao,
		Boolean ativo)
{
}
