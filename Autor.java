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

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public boolean adicionaLivro(Livro livro) {
		String auxPesquisa = livro.getIsbn();
		if (buscaLivro(auxPesquisa) == null) {
			livros.add(livro);
		}
		return false;
	}

	public Livro buscaLivro (String isbn) {
		for (Livro livro : livros) {
			if (livro.getIsbn().equals(isbn))
				return livro;
		}
		return null;
	}

	public ArrayList<Livro> pesquisaLivros() {
		if (!livros.isEmpty()) {
			return livros;
		}
		return null;
	}

}
