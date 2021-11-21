package com.buenoezandro.boot.util;

import java.util.List;

public class PaginacaoUtil<T> {

	private int tamanho;
	private int pagina;
	private long totalPaginas;
	private String direcao;
	private List<T> registros;

	public PaginacaoUtil(int tamanho, int pagina, long totalPaginas, String direcao, List<T> registros) {
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalPaginas = totalPaginas;
		this.direcao = direcao;
		this.registros = registros;
	}

	public int getTamanho() {
		return this.tamanho;
	}

	public int getPagina() {
		return this.pagina;
	}

	public long getTotalPaginas() {
		return this.totalPaginas;
	}

	public String getDirecao() {
		return this.direcao;
	}

	public List<T> getRegistros() {
		return this.registros;
	}
}
