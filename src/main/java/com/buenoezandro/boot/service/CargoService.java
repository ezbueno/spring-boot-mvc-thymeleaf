package com.buenoezandro.boot.service;

import java.util.List;

import com.buenoezandro.boot.domain.Cargo;
import com.buenoezandro.boot.util.PaginacaoUtil;

public interface CargoService {

	void salvar(Cargo cargo);

	void editar(Cargo cargo);

	void excluir(Long id);

	Cargo buscarPorId(Long id);

	List<Cargo> buscarTodos();
	
	boolean cargoContemFuncionarios(Long id);
	
	PaginacaoUtil<Cargo> buscarPorPagina(int pagina);
}
