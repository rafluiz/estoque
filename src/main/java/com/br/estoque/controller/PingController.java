package com.br.estoque.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conectividade")
public class PingController {
	
	@GetMapping
	public String ping()
	{
		return "Teste de Conexão feito com sucesso.";
	}

}
