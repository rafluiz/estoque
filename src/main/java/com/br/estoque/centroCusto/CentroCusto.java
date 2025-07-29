package com.br.estoque.centroCusto;


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
	
	public CentroCusto (DadosCadastroCentroCusto dados)
	{
		this.nome =  dados.nome();
		this.apelido =  dados.apelido();
		this.ativo =  dados.ativo();
	}

	public void atualizarInformacoes(DadosAtualizacaoCentroCusto dados) {
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}

		if (dados.apelido() != null) {
			this.apelido = dados.apelido();
		}
		
		if (dados.ativo() != null) {
			this.ativo = dados.ativo().booleanValue();
		}

		
	}

    public void excluir() {
        this.ativo = false;
    }
	
	

}
