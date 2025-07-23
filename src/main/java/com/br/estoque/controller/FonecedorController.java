package com.br.estoque.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fornecedor")
public class FonecedorController {
	
	@GetMapping
	public String teste()
	{
		return "Teste de Conexão feito com sucesso.";
	}
	
	@PostMapping
	public String grava()
	{
		return "Teste de Conexão feito com sucesso.";
	}
	
	@PutMapping
	public String atualiza()
	{
		return "Teste de Conexão feito com sucesso.";
	}
	
	@DeleteMapping
	public String exclui()
	{
		return "Teste de Conexão feito com sucesso.";
	}

}
