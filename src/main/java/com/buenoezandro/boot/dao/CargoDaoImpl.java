package com.buenoezandro.boot.dao;

import org.springframework.stereotype.Repository;

import com.buenoezandro.boot.domain.Cargo;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {
}
