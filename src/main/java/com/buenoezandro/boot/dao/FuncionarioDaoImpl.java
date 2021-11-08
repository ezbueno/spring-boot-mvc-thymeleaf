package com.buenoezandro.boot.dao;

import org.springframework.stereotype.Repository;

import com.buenoezandro.boot.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {
}
