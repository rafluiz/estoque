package com.br.estoque.domain.item;

import jakarta.validation.constraints.NotBlank;

public record DadosListagemItem (
		Long id,
		@NotBlank
		String SKU,
		@NotBlank
		String codigo,
		@NotBlank
		String descricao,
		@NotBlank
		boolean ativo)
{
	public DadosListagemItem(Item item) {
        this(item.getId(), item.getSKU(), item.getCodigo(), item.getDescricao(), item.isAtivo());
    }
}
