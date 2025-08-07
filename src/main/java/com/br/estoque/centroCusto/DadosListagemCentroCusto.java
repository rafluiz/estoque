package com.br.estoque.centroCusto;

import jakarta.validation.constraints.NotBlank;

public record DadosListagemCentroCusto (
		Long id,
		@NotBlank
		String nome,
		@NotBlank
		String apelido,
		@NotBlank
		boolean ativo)
{
	public DadosListagemCentroCusto(CentroCusto centroCusto) {
        this(centroCusto.getId(), centroCusto.getNome(), centroCusto.getApelido(), centroCusto.isAtivo());
    }
}
