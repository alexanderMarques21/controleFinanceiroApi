package com.alexssander.controleFinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexssander.controleFinanceiro.models.Lancamento;
import com.alexssander.controleFinanceiro.repositories.custom.LancamentoRepositoryQuery;

@Repository
public interface LancamentoRepository  extends JpaRepository<Lancamento, Integer>, LancamentoRepositoryQuery{
	
	

}
