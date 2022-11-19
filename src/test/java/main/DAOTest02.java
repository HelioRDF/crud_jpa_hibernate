package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import infra.DAO;
import modelo.Pessoa;

public class DAOTest02 {


	@Test
	public void buscarPessoaValidaNome() {
		Pessoa pessoa = new Pessoa();
		DAO<Pessoa> dao = new DAO<>(Pessoa.class);
		pessoa = (Pessoa) dao.encontrarPeloId(3);
		assertEquals("Aline", pessoa.getNome());
	}

	@Test
	public void buscarPessoaValidarIdade() {
		Pessoa pessoa = new Pessoa();
		DAO<Pessoa> dao = new DAO<>(Pessoa.class);
		pessoa = (Pessoa) dao.encontrarPeloId(4);
		assertEquals(29, pessoa.getIdade());
	}

	@Test
	public void tempoParaAdicionarPessoa() {

		long tempoInicial = System.currentTimeMillis();

		// execução do método
		Pessoa pessoa5 = new Pessoa("Daniela", 33);
		DAO<Pessoa> dao = new DAO<>(Pessoa.class);
		dao.abrir().create(pessoa5).fechar();

		long tempoFinal = System.currentTimeMillis() - tempoInicial;

		assertTrue(tempoFinal < 3000l);
		System.out.println("O método foi executado em " + tempoFinal + " ms");

	}
	

}
