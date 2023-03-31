import java.util.ArrayList;

public class Livro {

	private String isbn;

	private String titulo;

	private int ano;

	private ArrayList<Autor> autores;

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
		autores.add(autor);
		return false;
	}

}
