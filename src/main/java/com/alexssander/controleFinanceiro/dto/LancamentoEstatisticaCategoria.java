package com.alexssander.controleFinanceiro.dto;


public class LancamentoEstatisticaCategoria {
	
	private int tipo;
	private Double total;
	public LancamentoEstatisticaCategoria(int tipo, Double total) {
		super();
		this.tipo = tipo;
		this.total = total;
	}
	public LancamentoEstatisticaCategoria() {}

	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	
	

}
