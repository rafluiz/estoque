package com.br.estoque.domain.item;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "item")
@Entity(name = "Item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@Column(name="sku", nullable=false)
	private String SKU;
	@Column(name="codigo", nullable=true)
	private String codigo;
	@Column(name="descricao", nullable=false)
	private String descricao;
    @Column(name="quantidadeEstoque", nullable=false)
	private BigDecimal quantidadeEstoque;
    @Column(name="ativo", nullable=false)
	private boolean ativo;
	
	public Item (DadosCadastroItem dados)
	{
		this.SKU =  dados.SKU();
		this.codigo =  dados.codigo();
		this.descricao =  dados.descricao();
	}

	public void atualizarInformacoes(DadosAtualizacaoItem dados) {
		if (dados.SKU() != null) {
			this.SKU = dados.SKU();
		}

		if (dados.codigo() != null) {
			this.codigo = dados.codigo();
		}
		
		if (dados.descricao() != null) {
			this.descricao = dados.descricao();
		}
	}

    public void excluir() {
        this.ativo = false;
    }

}
