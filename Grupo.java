import java.util.ArrayList;

public class Grupo {

	private ArrayList<Autor> autores;

	public boolean cadastraAutor(Autor autor) {
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
