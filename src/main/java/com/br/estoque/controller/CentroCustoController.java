package com.br.estoque.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	public ResponseEntity<Page<DadosListagemCentroCusto>> listar(@RequestParam(required = false, defaultValue = "false") boolean ativos, @PageableDefault(size = 50, sort = {"nome"}) Pageable paginacao) {
		Page<DadosListagemCentroCusto> page = new PageImpl<>(new ArrayList<DadosListagemCentroCusto>(), paginacao, 1);

		if (ativos) {
			page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCentroCusto::new);
		}
		else {
			page = repository.findAll(paginacao).map(DadosListagemCentroCusto::new);
		}
		
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosListagemCentroCusto> detalhar(@PathVariable Long id) {
		CentroCusto centroCusto =  repository.getReferenceById(id.longValue());
		
		return ResponseEntity.ok(new DadosListagemCentroCusto(centroCusto));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DadosListagemCentroCusto> cadastrar(@RequestBody @Valid DadosCadastroCentroCusto dados, UriComponentsBuilder uriBuilder)
	{
		var centroCusto = new CentroCusto(dados);
		repository.save(centroCusto);
		
		var uri = uriBuilder.path("/api/centrocusto/{id}").buildAndExpand(centroCusto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosListagemCentroCusto(centroCusto));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosListagemCentroCusto> atualizar(@RequestBody @Valid DadosAtualizacaoCentroCusto dados)
	{
		CentroCusto centroCusto =  repository.getReferenceById(dados.id().longValue());
		
		centroCusto.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosListagemCentroCusto(centroCusto));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> excluir(@PathVariable Long id) {
		CentroCusto centroCusto =  repository.getReferenceById(id.longValue());
		
		centroCusto.excluir();

		return ResponseEntity.noContent().build();
	}

}
