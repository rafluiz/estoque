package com.br.estoque.centroCusto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCentroCusto (
		@NotNull
		Long id,
		String nome,
		String apelido,
		Boolean ativo)
{
}
