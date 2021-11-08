package com.buenoezandro.boot.dao;

import org.springframework.stereotype.Repository;

import com.buenoezandro.boot.domain.Departamento;

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {
}
