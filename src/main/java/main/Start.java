package main;
import infra.DAO;
import modelo.Pessoa;

public class Start {

	public static void main(String[] args) {
		 cadastrarPessoa();
		//buscarPessoa(3);
		//deletarPessoa(6);
	}

	public static void cadastrarPessoa() {
		Pessoa pessoa1 = new Pessoa("João", 33);
		Pessoa pessoa2 = new Pessoa("Ester", 10);
		Pessoa pessoa3 = new Pessoa("Aline", 30);
		Pessoa pessoa4 = new Pessoa("Luzia", 60);
		DAO<Pessoa> dao = new DAO<>(Pessoa.class);

		dao.abrir().create(pessoa1).create(pessoa2).create(pessoa3).create(pessoa4).fechar();

	}

	public static void buscarPessoa(int id) {
		DAO<Pessoa> dao = new DAO<>(Pessoa.class);
		Pessoa pessoa = dao.encontrarPeloId(id);
		System.out.println("\n\nPessoa: " + pessoa);
	}

	public static void deletarPessoa(int id) {
		DAO<Pessoa> dao = new DAO<>(Pessoa.class);
		dao.abrir().delete(id).fechar();

	}
	
	public static void atualizarPessoa() {
		DAO<Pessoa> dao = new DAO<>(Pessoa.class);	
				dao.atualizar(2, "Renato", 25);

	}

}