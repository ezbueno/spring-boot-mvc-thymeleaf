package com.buenoezandro.boot.dao;

import java.time.LocalDate;
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

	@Override
	public List<Funcionario> findByCargoId(@Param(value = "id") Long id) {
		return this.createQuery("SELECT f FROM Funcionario f WHERE f.cargo.id = ?1", id);
	}

	@Override
	public List<Funcionario> findByDataEntradaDataSaida(@Param(value = "entrada") LocalDate entrada,
			@Param(value = "saida") LocalDate saida) {
		String jpql = new StringBuilder("SELECT f FROM Funcionario f ")
				.append("WHERE f.dataEntrada >= ?1 AND f.dataSaida <= ?2 ").append("ORDER BY f.dataEntrada ASC")
				.toString();
		return this.createQuery(jpql, entrada, saida);
	}

	@Override
	public List<Funcionario> findByDataEntrada(@Param(value = "entrada") LocalDate entrada) {
		String jpql = new StringBuilder("SELECT f FROM Funcionario f ").append("WHERE f.dataEntrada = ?1 ")
				.append("ORDER BY f.dataEntrada ASC").toString();
		return this.createQuery(jpql, entrada);
	}

	@Override
	public List<Funcionario> findByDataSaida(@Param(value = "saida") LocalDate saida) {
		String jpql = new StringBuilder("SELECT f FROM Funcionario f ").append("WHERE f.dataSaida = ?1 ")
				.append("ORDER BY f.dataEntrada ASC").toString();
		return this.createQuery(jpql, saida);
	}
}
