package com.buenoezandro.boot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.boot.dao.CargoDao;
import com.buenoezandro.boot.domain.Cargo;
import com.buenoezandro.boot.service.CargoService;
import com.buenoezandro.boot.util.PaginacaoUtil;

@Transactional(readOnly = false)
@Service
public class CargoServiceImpl implements CargoService {

	private final CargoDao cargoDao;

	public CargoServiceImpl(CargoDao cargoDao) {
		this.cargoDao = cargoDao;
	}

	@Override
	public void salvar(Cargo cargo) {
		this.cargoDao.save(cargo);
	}

	@Override
	public void editar(Cargo cargo) {
		this.cargoDao.update(cargo);
	}

	@Override
	public void excluir(Long id) {
		this.cargoDao.delete(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Cargo buscarPorId(Long id) {
		return this.cargoDao.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Cargo> buscarTodos() {
		return this.cargoDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public boolean cargoContemFuncionarios(Long id) {
		return !this.buscarPorId(id).getFuncionarios().isEmpty();		
	}

	@Override
	public PaginacaoUtil<Cargo> buscarPorPagina(int pagina, String direcao) {
		return this.cargoDao.buscaPaginada(pagina, direcao);
	}
}
