import java.util.ArrayList;

public class Livro {

	private String isbn;

	private String titulo;

	private int ano;

	private ArrayList<Autor> autores;

	public boolean adicionaAutor(Autor autor) {
		autores.add(autor);
		return false;
	}

}
