package com.buenoezandro.boot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.boot.dao.FuncionarioDao;
import com.buenoezandro.boot.domain.Funcionario;
import com.buenoezandro.boot.service.FuncionarioService;

@Transactional(readOnly = true)
@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private final FuncionarioDao funcionarioDao;
	
	public FuncionarioServiceImpl(FuncionarioDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void salvar(Funcionario funcionario) {
		this.funcionarioDao.save(funcionario);
	}

	@Transactional(readOnly = false)
	@Override
	public void editar(Funcionario funcionario) {
		this.funcionarioDao.update(funcionario);
	}

	@Transactional(readOnly = false)
	@Override
	public void excluir(Long id) {
		this.funcionarioDao.delete(id);
	}

	@Override
	public Funcionario buscarPorId(Long id) {
		return this.funcionarioDao.findById(id);
	}

	@Override
	public List<Funcionario> buscarTodos() {
		return this.funcionarioDao.findAll();
	}
}
