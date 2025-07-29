package com.br.estoque.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;

import com.br.estoque.centroCusto.DadosCadastroCentroCusto;
import com.br.estoque.centroCusto.CentroCusto;
import com.br.estoque.centroCusto.DadosAtualizacaoCentroCusto;
import com.br.estoque.centroCusto.DadosListagemCentroCusto;
import com.br.estoque.centroCusto.CentroCustoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/centrocusto")
public class CentroCustoController {
	
	@Autowired
	private CentroCustoRepository repository;
	
	@GetMapping
	public Page<DadosListagemCentroCusto> listar(@RequestParam(required = false, defaultValue = "false") boolean ativos, @PageableDefault(size = 50, sort = {"nome"}) Pageable paginacao) {
		if (ativos) {
			return repository.findAllByAtivoTrue(paginacao).map(DadosListagemCentroCusto::new);
		}
		
		// Se ativos for false, retorna todos os registros, incluindo os inativos
		return repository.findAll(paginacao).map(DadosListagemCentroCusto::new);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosListagemCentroCusto> detalhar(@PathVariable Long id) {
		CentroCusto centroCusto =  repository.getReferenceById(id.longValue());
		return ResponseEntity.ok(new DadosListagemCentroCusto(centroCusto));
	}

	@PostMapping
	@Transactional
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCentroCusto dados)
	{
		var centroCusto = new CentroCusto(dados);
		repository.save(centroCusto);
		
		var uri = UriBuilder .path("/api/centrocusto/{id}").buildAndExpand(centroCusto.getId()).toUri();
		return ResponseEntity.created(null.)
	}
	
	@PutMapping
	@Transactional
	public String atualizar(@RequestBody @Valid DadosAtualizacaoCentroCusto dados)
	{
		CentroCusto centroCusto =  repository.getReferenceById(dados.id().longValue());
		centroCusto.atualizarInformacoes(dados);
		return "Teste de Conexão feito com sucesso.";
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public String excluir(@PathVariable Long id) {
		CentroCusto centroCusto =  repository.getReferenceById(id.longValue());
		centroCusto.excluir();

		return "Teste de Conexão feito com sucesso.";
	}

}
