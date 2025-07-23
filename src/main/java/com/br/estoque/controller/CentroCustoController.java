package com.br.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.estoque.model.CentroCusto;
import com.br.estoque.record.DadosCentroCusto;
import com.br.estoque.record.DadosListagemCentroCusto;
import com.br.estoque.repository.CentroCustoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/centrocusto")
public class CentroCustoController {
	
	@Autowired
	private CentroCustoRepository repository;
	
	@GetMapping
	public List<DadosListagemCentroCusto> listar() {
		return repository.findAll().stream().map(DadosListagemCentroCusto::new).toList();
    }
	
	@PostMapping
	@Transactional
	public String cadastrar(@RequestBody @Valid DadosCentroCusto dados)
	{
		repository.save(new CentroCusto(dados));
		return "Teste de Conexão feito com sucesso.";
	}
	
	@PutMapping
	public String atualizar()
	{
		return "Teste de Conexão feito com sucesso.";
	}
	
	@DeleteMapping
	public String excluir()
	{
		return "Teste de Conexão feito com sucesso.";
	}

}
