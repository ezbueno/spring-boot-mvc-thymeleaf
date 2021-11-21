package com.buenoezandro.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.buenoezandro.boot.domain.Cargo;
import com.buenoezandro.boot.util.PaginacaoUtil;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

	public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao) {
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;

		List<Cargo> cargos = this.getEntityManager()
				.createQuery("SELECT c FROM Cargo c ORDER BY c.nome " + direcao, Cargo.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho).getResultList();
		
		long totalRegistros = this.count();
		long totalPaginas = (totalRegistros + (tamanho - 1)) / tamanho;

		return new PaginacaoUtil<>(tamanho, pagina, totalPaginas, direcao, cargos);
	}

	public long count() {
		return this.getEntityManager().createQuery("SELECT COUNT(*) FROM Cargo", Long.class).getSingleResult();
	}
}
