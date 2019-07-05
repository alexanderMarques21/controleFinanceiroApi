package com.alexssander.controleFinanceiro.repositories.custom;

import java.util.List;

import com.alexssander.controleFinanceiro.dto.LancamentoEstatisticaCategoria;

public interface LancamentoRepositoryQuery {

	public List<LancamentoEstatisticaCategoria> countLancamentosByCategoria();

}
