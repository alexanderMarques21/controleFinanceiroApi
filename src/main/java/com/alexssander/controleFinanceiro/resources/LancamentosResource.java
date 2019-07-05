package com.alexssander.controleFinanceiro.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alexssander.controleFinanceiro.dto.LancamentoEstatisticaCategoria;
import com.alexssander.controleFinanceiro.models.Lancamento;
import com.alexssander.controleFinanceiro.services.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentosResource {

	@Autowired
	private LancamentoService lancamentoService;

	@GetMapping
	public ResponseEntity<List<Lancamento>> listar() {

		List<Lancamento> lancamentos = lancamentoService.listar();

		return ResponseEntity.ok().body(lancamentos);

	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Lancamento>> listarComPaginacao(
			@RequestParam(defaultValue="0")Integer page,
			@RequestParam(defaultValue="vencimento") String ordenadoPor,
			@RequestParam (defaultValue ="24") Integer linhasPorPagina,
			@RequestParam(defaultValue="DESC") String direcao){
		
		Page<Lancamento> list = lancamentoService.listarComPaginacao(page, linhasPorPagina, direcao,ordenadoPor );
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> buscar(@PathVariable Integer id) {
		
		Lancamento lancamentoSalvo = lancamentoService.buscar(id);
		
		return ResponseEntity.ok().body(lancamentoSalvo);
		
	}
	
	@GetMapping("/estatisticas/por-categoria")
	public ResponseEntity<List<LancamentoEstatisticaCategoria>> listarDadosGrafico(){
		
		List<LancamentoEstatisticaCategoria> lancamentosEstatistica = lancamentoService.listarDadosGrafico();
		
		return ResponseEntity.ok().body(lancamentosEstatistica);
		

	}
	

	@PostMapping
	public ResponseEntity<Lancamento> salvar(@RequestBody @Valid Lancamento lancamento) {
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(lancamentoSalvo.getId())
				.toUri();

		return ResponseEntity.created(uri).body(lancamentoSalvo);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody @Valid Lancamento lancamento, @PathVariable Integer id) {
		lancamento.setId(id);
		lancamentoService.atualizar(lancamento);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id){
		
		lancamentoService.deletar(id);
		
		return ResponseEntity.noContent().build();
	}

}