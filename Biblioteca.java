import java.util.ArrayList;

public class Biblioteca {

	private ArrayList<Livro> livros;

	public boolean cadastraLivro(Livro livro) {
		String auxPesquisa = livro.getIsbn();
		if (pesquisaLivro(auxPesquisa) == null) {
			livros.add(livro);
			return true;
		}
		return false;
	}

	public Livro pesquisaLivro(String isbn) {
		if(!livros.isEmpty()) {
			for (Livro livro : livros) {
				if (livro.getIsbn().equals(isbn))
					return livro;
			}
		}
		return null;
	}

	public ArrayList<Livro> pesquisaLivro(int ano) {
		ArrayList<Livro> aux = new ArrayList<>();

		for (Livro livro : livros) {
			if (livro.getAno() == ano) {
				aux.add(livro);
			}
		}

		if (!aux.isEmpty())
			return aux;

		return null;
	}

}
