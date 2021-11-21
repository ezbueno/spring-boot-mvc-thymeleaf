package com.buenoezandro.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.buenoezandro.boot.domain.Cargo;
import com.buenoezandro.boot.util.PaginacaoUtil;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

	public PaginacaoUtil<Cargo> buscaPaginada(int pagina) {
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		
		List<Cargo> cargos = this.getEntityManager()
				.createQuery("SELECT c FROM Cargo c ORDER BY c.nome ASC", Cargo.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		return new PaginacaoUtil<>(tamanho, pagina, 0, cargos);
	}
}
