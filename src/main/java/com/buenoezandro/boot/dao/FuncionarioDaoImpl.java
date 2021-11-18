package com.buenoezandro.boot.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buenoezandro.boot.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

	@Override
	public List<Funcionario> findByNome(@Param(value = "nome") String nome) {
		return this.createQuery("SELECT f FROM Funcionario f WHERE f.nome LIKE CONCAT('%', ?1, '%')", nome);
	}
}
