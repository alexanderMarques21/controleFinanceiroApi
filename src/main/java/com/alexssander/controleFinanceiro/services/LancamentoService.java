package com.alexssander.controleFinanceiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alexssander.controleFinanceiro.dto.LancamentoEstatisticaCategoria;
import com.alexssander.controleFinanceiro.models.Lancamento;
import com.alexssander.controleFinanceiro.repositories.LancamentoRepository;
import com.alexssander.controleFinanceiro.services.exceptions.ObjectNotFoundException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	


	public List<Lancamento> listar() {

		return lancamentoRepository.findAll();
	}
		
	public Page<Lancamento> listarComPaginacao(Integer page, Integer linhasPorPagina, String direcao,String ordenadoPor){
		
		PageRequest pageRequest = PageRequest.of(page, linhasPorPagina, Direction.valueOf(direcao), ordenadoPor);
		
		return lancamentoRepository.findAll(pageRequest);
		
	}
	
	public List<LancamentoEstatisticaCategoria> listarDadosGrafico(){
		
		return  lancamentoRepository.countLancamentosByCategoria();
		
	}
	

	public Lancamento salvar(Lancamento lancamento) {

		Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);

		return lancamentoSalvo;

	}

	public void atualizar(Lancamento lancamento) {

		Lancamento lancamentoSalvo = buscar(lancamento.getId());
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");
		lancamentoRepository.save(lancamentoSalvo);

	}

	public void deletar(Integer id) {

		Lancamento lancamentoSalvo = buscar(id);
		lancamentoRepository.delete(lancamentoSalvo);
	}

	public Lancamento buscar(Integer id) {

		Optional<Lancamento> lancamentoSalvo = lancamentoRepository.findById(id);

		if (!lancamentoSalvo.isPresent()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Lancamento.class.getName());
		}

		return lancamentoSalvo.get();
	}

}
