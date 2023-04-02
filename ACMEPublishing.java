import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ACMEPublishing {

	private Biblioteca biblioteca;

	private Grupo grupo;

	private Scanner entrada = null; // Atributo para entrada de dados

	// Construtor
	public ACMEPublishing() {
		try {
			BufferedReader streamEntrada = new BufferedReader(new FileReader("dados2.txt"));
			entrada = new Scanner(streamEntrada); // Usa como entrada um arquivo
			PrintStream streamSaida =
					new PrintStream(new File("saida2.txt"), Charset.forName("UTF-8"));
			System.setOut(streamSaida); // Usa como saida um arquivo
		} catch (Exception e) {
			System.out.println(e);
		}
		entrada.useLocale(Locale.ENGLISH);
	}

	public void executa() {
		Biblioteca biblioteca = new Biblioteca(); // para todos livros cadastrados

		// PASSO 1: CADASTRAR LIVROS
		while (entrada.hasNextLine()) {
			String isbn = entrada.nextLine(); // isbn lido do livro
			if (isbn.equals("-1"))  // sempre que ler "-1", ir para prox funcionalidade
				break;

			// proximas 2 linhas depois do Id vao ser Nome e Ano respectivamente
			String infoLivro = entrada.nextLine() + "  " + entrada.nextLine();
			String[] campos = infoLivro.split("  ");
			String nome = campos[0];
			int ano = Integer.parseInt(campos[1]);

			Livro livro = new Livro(isbn, nome, ano);
			if(biblioteca.cadastraLivro(livro))
				System.out.printf("1;%s;%s;%d\n", isbn, nome, ano);
		}

		// PASSO 2: MOSTRA QTD DE LIVROS CADASTRADOS
		int qtdLivrosPasso2 = biblioteca.getLivros().size();
		System.out.printf("2;%d\n", qtdLivrosPasso2);

		Grupo grupo = new Grupo(); // para todos autores cadastrados
		// PASSO 3: CADASTRAR AUTORES
		while (entrada.hasNextLine()) {
			int id = Integer.parseInt(entrada.nextLine()); // Id lido do Autor
			if (id == -1)
				break;

			// proximas 2 linhas depois do Id vao ser Nome e isbn respectivamente
			String infoAutores = entrada.nextLine() + "," + entrada.nextLine();
			String[] campos = infoAutores.split(",");
			String nome = campos[0];
			String isbn = campos[1];

			Livro auxLivro = biblioteca.pesquisaLivro(isbn); // pode ser null
			if (grupo.pesquisaAutor(id) == null) {
				Autor autor = new Autor(id, nome, auxLivro);
				grupo.cadastraAutor(autor);
				auxLivro.adicionaAutor(autor);
				System.out.printf("3;%d;%s;%s\n", id, nome, isbn);
			}
		}
		// PASSO 4: MOSTRAR QTD DE AUTORES CADASTRADOS
		int qtdAutoresPasso4 = grupo.getAutores().size();
		System.out.printf("4;%d\n", qtdAutoresPasso4);

		// PASSO 5: ADICIONAR LIVRO A UM AUTOR
		while (entrada.hasNextLine()) {
			int id = Integer.parseInt(entrada.nextLine()); // Id lido do autor
			if (id == -1)
				break;
			String isbn = entrada.nextLine();

			Autor auxAutor = grupo.pesquisaAutor(id);
			Livro auxLivro = biblioteca.pesquisaLivro(isbn);

			// adicionar livro ao autor e autor ao livro
			if(auxAutor.adicionaLivro(auxLivro) && auxLivro.adicionaAutor(auxAutor)) {
				// buscar infos que faltam do autor
				String nome = auxAutor.getNome();
				// buscar infos que faltam do livro
				String titulo = auxLivro.getTitulo();
				int ano = auxLivro.getAno();
				System.out.printf("5;%d;%s;%s;%s;%d\n", id, nome, isbn, titulo, ano);
			}
		}
		// PASSO 6: MOSTRAR LIVROS DE UM AUTOR
		int codAutorPasso6 = Integer.parseInt(entrada.nextLine());
		Autor autorPasso6 = grupo.pesquisaAutor(codAutorPasso6);
		String nome = autorPasso6.getNome();
		for (Livro livro : autorPasso6.pesquisaLivros()) {
			int ano = livro.getAno();
			String titulo = livro.getTitulo();
			String isbn = livro.getIsbn();
			System.out.printf("6;%d;%s;%s;%s;%d\n", codAutorPasso6, nome, isbn, titulo, ano);
		}

		// PASSO 7: MOSTRAR AUTORES DE UM LIVRO
		String isbnPasso7 = entrada.nextLine();
		Livro livroPasso7 = biblioteca.pesquisaLivro(isbnPasso7);
		StringBuilder nomeAutores = new StringBuilder();
		for (Autor autor : livroPasso7.getAutores()) {
			if (nomeAutores.length() > 0) {
				nomeAutores.append(";");
			}
			nomeAutores.append(autor.getNome());
		}

		System.out.printf("7;%s;%s\n", isbnPasso7, nomeAutores.toString());

		// PASSO 8: MOSTRAR TITULO DE LIVROS QUE POSSUEM MAIS DE 1 AUTOR
		for (Livro livro : biblioteca.getLivros()) {
			if (livro.getAutores().size() > 1) {
				String isbn = livro.getIsbn();
				String titulo = livro.getTitulo();
				System.out.printf("8;%s;%s\n", isbn, titulo);
			}
		}

		// PASSO 9: MOSTRAR NOMES DE AUTORES COM MAIS DE UM LIVRO
		StringBuilder isbnLivros = new StringBuilder();
		for (Autor autor : grupo.getAutores()) {
			ArrayList<Livro> aux = autor.pesquisaLivros();
			if (aux.size() > 1) {
				for (Livro livro : aux) {
					if (isbnLivros.length() > 0) {
						isbnLivros.append(";");
					}
					isbnLivros.append(livro.getIsbn());
				}
				String auxNome = autor.getNome();
				System.out.printf("9;%s;%s\n", auxNome, isbnLivros.toString());
			}
		}

		// PASSO 10: MOSTRAR LIVROS DE UM DETERMINADO ANO
		int anoLivroPasso10 = Integer.parseInt(entrada.nextLine());
		for (Livro livro : biblioteca.pesquisaLivro(anoLivroPasso10)) {
			String isbn = livro.getIsbn();
			String titulo = livro.getTitulo();
			System.out.printf("10;%s;%s;%d\n", isbn, titulo, anoLivroPasso10);
		}
	}

}
