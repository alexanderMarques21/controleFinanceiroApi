package com.alexssander.controleFinanceiro.repositories.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.alexssander.controleFinanceiro.dto.LancamentoEstatisticaCategoria;
import com.alexssander.controleFinanceiro.models.Lancamento;


public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	public List <LancamentoEstatisticaCategoria> countLancamentosByCategoria() {
	
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<LancamentoEstatisticaCategoria> criteriaQuery = criteriaBuilder
				.createQuery(LancamentoEstatisticaCategoria.class);
		
		Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
		
		criteriaQuery.select(criteriaBuilder.construct(LancamentoEstatisticaCategoria.class,
				root.get("tipo"),criteriaBuilder.sum(root.get("valor"))));
		
		criteriaQuery.groupBy(root.get("tipo"));
		
		TypedQuery<LancamentoEstatisticaCategoria> typedQuery = 
				em.createQuery(criteriaQuery);
		
	
		
		return typedQuery.getResultList();
	}

}
