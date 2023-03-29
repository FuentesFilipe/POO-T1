import java.util.ArrayList;

public class Autor {

	private int codigo;

	private String nome;

	private ArrayList<Livro> livros;

	public Autor(int codigo, String nome, Livro livro) {
		this.codigo = codigo;
		this.nome = nome;
		adicionaLivro(livro);
	}

	public boolean adicionaLivro(Livro livro) {
		return false;
	}

	public ArrayList<Livro> pesquisaLivros() {

		return null;
	}

}
