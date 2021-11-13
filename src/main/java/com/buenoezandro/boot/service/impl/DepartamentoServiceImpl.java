package com.buenoezandro.boot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.boot.dao.DepartamentoDao;
import com.buenoezandro.boot.domain.Departamento;
import com.buenoezandro.boot.service.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	private final DepartamentoDao departamentoDao;

	public DepartamentoServiceImpl(DepartamentoDao departamentoDao) {
		this.departamentoDao = departamentoDao;
	}

	@Transactional(readOnly = false)
	@Override
	public void salvar(Departamento departamento) {
		this.departamentoDao.save(departamento);
	}

	@Transactional(readOnly = false)
	@Override
	public void editar(Departamento departamento) {
		this.departamentoDao.update(departamento);
	}

	@Transactional(readOnly = false)
	@Override
	public void excluir(Long id) {
		this.departamentoDao.delete(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Departamento buscarPorId(Long id) {
		return this.departamentoDao.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Departamento> buscarTodos() {
		return this.departamentoDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public boolean departamentoContemCargos(Long id) {
		return !this.buscarPorId(id).getCargos().isEmpty();
	}
}
