package br.com.db1.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import br.com.db1.model.Cidade;
import br.com.db1.model.Uf;
import br.com.db1.model.Uf2;

public class CidadeTest {


	@Test
	public void updateTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db1start");
		EntityManager manager = factory.createEntityManager();

		Cidade cidade = manager.find(Cidade.class, 2L);
		cidade.setNome("Maringa");
		manager.getTransaction().begin();
		manager.persist(cidade);
		manager.getTransaction().commit();

		factory.close();

	}

	@Test
	public void selectHqlTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db1start");
		EntityManager manager = factory.createEntityManager();

		Query query = manager.createQuery("Select c from Cidade c");
		List<Cidade> cidades = query.getResultList();

		for (Cidade cidade : cidades) {
			System.out.println(cidade.getNome());
			System.out.println(cidade.getId());
			System.out.println(cidade.getUf().getNome());
		}
	}

	@Test
	public void selectSqlNativoTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("db1start");
		EntityManager manager = factory.createEntityManager();

		Query query = manager.createNativeQuery("Select * from cidade c");
		List<Cidade> cidades = query.getResultList();

		factory.close();

	}
}
