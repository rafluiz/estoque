package com.br.estoque.record;


import com.br.estoque.model.CentroCusto;

import jakarta.validation.constraints.NotBlank;

public record DadosListagemCentroCusto (
		
		String id, 
		@NotBlank
		String nome, 
		@NotBlank
		String apelido, 
		@NotBlank
		boolean ativo) {
	
	public DadosListagemCentroCusto(CentroCusto centroCusto) {
        this(centroCusto.getId(), centroCusto.getNome(), centroCusto.getApelido(), centroCusto.isAtivo());
    }

	
	
}
