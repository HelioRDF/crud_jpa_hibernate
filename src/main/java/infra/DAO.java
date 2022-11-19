package infra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Pessoa;

public class DAO<E> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> entidade;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("jpa_exemplo");
		} catch (Exception e) {
			System.out.println(e.getCause());
			throw new RuntimeException(e.getMessage());
		}
	}

	public DAO(Class<E> entidade) {
		this.entidade = entidade;
		em = emf.createEntityManager();
	}

	public DAO<E> abrir() {
		em.getTransaction().begin();
		return this;
	}

	public DAO<E> fechar() {
		em.getTransaction().commit();
		return this;
	}

	public DAO<E> create(E entidade) {
		em.persist(entidade);
		return this;
	}

	public E encontrarPeloId(Object id) {
		return em.find(entidade, id);
	}

	public DAO<E> delete(int id) {
		DAO<E> dao = new DAO<E>(entidade);
		E pessoa = dao.encontrarPeloId(id);
		System.out.println("\n\nDeletando... " + pessoa);
		em.remove(em.contains(pessoa) ? pessoa : em.merge(pessoa));
		return this;
	}

	public Pessoa atualizar(int id, String nome, int idade) {
		DAO<E> dao = new DAO<E>(entidade);
		dao.abrir();

		Pessoa pessoa = (Pessoa) dao.encontrarPeloId(id);
		pessoa.setNome(nome);
		pessoa.setIdade(idade);

		em.merge(pessoa);

		dao.fechar();

		return pessoa;
	}
}