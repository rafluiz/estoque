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

import com.br.estoque.domain.item.Item;
import com.br.estoque.domain.item.ItemRepository;
import com.br.estoque.domain.item.DadosAtualizacaoItem;
import com.br.estoque.domain.item.DadosCadastroItem;
import com.br.estoque.domain.item.DadosListagemItem;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/item")
public class ItemController {
	
	@Autowired
	private ItemRepository repository;
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemItem>> listar(@RequestParam(required = false, defaultValue = "false") boolean ativos, @PageableDefault(size = 50, sort = {"nome"}) Pageable paginacao) {
		Page<DadosListagemItem> page = new PageImpl<>(new ArrayList<DadosListagemItem>(), paginacao, 1);

		if (ativos) {
			page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemItem::new);
		}
		else {
			page = repository.findAll(paginacao).map(DadosListagemItem::new);
		}
		
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosListagemItem> detalhar(@PathVariable Long id) {
		Item item =  repository.getReferenceById(id.longValue());
		
		return ResponseEntity.ok(new DadosListagemItem(item));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DadosListagemItem> cadastrar(@RequestBody @Valid DadosCadastroItem dados, UriComponentsBuilder uriBuilder)
	{
		var item = new Item(dados);
		repository.save(item);
		
		var uri = uriBuilder.path("/api/centrocusto/{id}").buildAndExpand(item.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosListagemItem(item));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosListagemItem> atualizar(@RequestBody @Valid DadosAtualizacaoItem dados)
	{
		Item item =  repository.getReferenceById(dados.id().longValue());
		
		item.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosListagemItem(item));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> excluir(@PathVariable Long id) {
		Item item =  repository.getReferenceById(id.longValue());
		
		item.excluir();

		return ResponseEntity.noContent().build();
	}
}
