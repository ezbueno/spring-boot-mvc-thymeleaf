package com.buenoezandro.boot.service;

import java.util.List;

import com.buenoezandro.boot.domain.Cargo;

public interface CargoService {

	void salvar(Cargo cargo);

	void editar(Cargo cargo);

	void excluir(Long id);

	Cargo buscarPorId(Long id);

	List<Cargo> buscarTodos();
}
