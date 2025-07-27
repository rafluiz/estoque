package com.br.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

import com.br.estoque.model.CentroCusto;
import com.br.estoque.record.DadosCadastroCentroCusto;
import com.br.estoque.record.DadosAtualizacaoCentroCusto;
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
	public Page<DadosListagemCentroCusto> listar(@RequestParam(required = false, defaultValue = "false") boolean ativos, 
	@PageableDefault(size = 50, sort = {"nome"}) Pageable paginacao) {
		
		if (ativos) {
			return repository.findAllByAtivoTrue(paginacao).map(DadosListagemCentroCusto::new);
		}
		// Se ativos for false, retorna todos os registros, incluindo os inativos
		return repository.findAll(paginacao).map(DadosListagemCentroCusto::new);
	}

	@PostMapping
	@Transactional
	@ResponseStatus(code = HttpStatus.CREATED)
	public void cadastrar(@RequestBody @Valid DadosCadastroCentroCusto dados)
	{
		repository.save(new CentroCusto(dados));
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
		//repository.deleteById(id);

		CentroCusto centroCusto =  repository.getReferenceById(id.longValue());
		centroCusto.excluir();

		return "Teste de Conexão feito com sucesso.";
	}

}
