package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import infra.DAO;
import modelo.Pessoa;

public class DAOTest {


	@Test
	public void buscarPessoaValidaNome() {
		DAO<Pessoa> dao = new DAO<>(Pessoa.class);
		Pessoa pessoa = new Pessoa();
		pessoa = (Pessoa) dao.encontrarPeloId(1);
		
		assertEquals("João", pessoa.getNome());
	}

	@Test
	public void buscarPessoaValidarIdade() {
		DAO<Pessoa> dao = new DAO<>(Pessoa.class);
		Pessoa pessoa = new Pessoa();
		pessoa = (Pessoa) dao.encontrarPeloId(2);
		
		assertEquals(10, pessoa.getIdade());
	}

	@Test
	public void tempoParaAdicionarPessoa() {
		long tempoInicial = System.currentTimeMillis();

		DAO<Pessoa> dao = new DAO<>(Pessoa.class);
		Pessoa pessoa5 = new Pessoa("Daniela", 33);
		dao.abrir().create(pessoa5).fechar();
		long tempoFinal = System.currentTimeMillis() - tempoInicial;

		assertTrue(tempoFinal < 3000l);
		
	}
	
}
