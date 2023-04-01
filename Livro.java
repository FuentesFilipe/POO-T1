import java.util.ArrayList;

public class Livro {

	private String isbn;

	private String titulo;

	private int ano;

	private ArrayList<Autor> autores;

	public Livro(String isbn, String titulo, int ano) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.ano = ano;
		autores = new ArrayList<>(5);
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAno() {
		return ano;
	}

	public ArrayList<Autor> getAutores() {
		return autores;
	}

	public boolean adicionaAutor(Autor autor) {
		int auxPesquisa = autor.getCodigo();
		if (pesquisaAutor(auxPesquisa) == null) {
			autores.add(autor);
			return true;
		}
		return false;
	}

	public Autor pesquisaAutor(int codigo) {
		for (Autor autor : autores) {
			if (autor.getCodigo() == codigo) {
				return autor; // achou o autor, retorna o obj
			}
		}
		return null; // nao existe o autor
	}
}
