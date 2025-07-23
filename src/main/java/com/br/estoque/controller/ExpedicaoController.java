
package com.br.estoque.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expedicao")
public class ExpedicaoController {
	
	@GetMapping
	public String lista()
	{
		return "Teste de Conexão feito com sucesso.";
	}
	
	@PostMapping
	public String cadastrar(@RequestBody String te)
	{
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
