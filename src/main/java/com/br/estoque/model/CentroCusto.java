package com.br.estoque.model;


import com.br.estoque.record.DadosCentroCusto;

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

@Table(name = "centrocusto")
@Entity(name = "CentroCusto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CentroCusto {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@Column(name="nome", nullable=false)
	private String nome;
	@Column(name="apelido", nullable=true)
	private String apelido;
	@Column(name="ativo", nullable=false)
	private boolean ativo;
	
	public CentroCusto (DadosCentroCusto dados)
	{
		this.nome =  dados.nome();
		this.apelido =  dados.apelido();
		this.ativo =  dados.ativo();
	}
	
	public CentroCusto ()
	{
		
		
	}

}
