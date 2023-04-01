import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMEPublishing {

	private Biblioteca biblioteca;

	private Grupo grupo;

	private Scanner entrada = null; // Atributo para entrada de dados
	// Construtor
	public ACMEPublishing() {
		try {
			BufferedReader streamEntrada = new BufferedReader(new FileReader("dados.txt"));
			entrada = new Scanner(streamEntrada); // Usa como entrada um arquivo
			PrintStream streamSaida =
					new PrintStream(new File("saida.txt"), Charset.forName("UTF-8"));
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
			String infoLivro = entrada.nextLine() + "," + entrada.nextLine();
			String[] campos = infoLivro.split(",");
			String nome = campos[0];
			int ano = Integer.parseInt(campos[1]);

			Livro livro = new Livro(isbn, nome, ano);
			biblioteca.cadastraLivro(livro);
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
			if (grupo.pesquisaAutor(id) == null && auxLivro != null) {
				Autor autor = new Autor(id, nome, auxLivro);
				grupo.cadastraAutor(autor);
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

			// adicionar livro ao autor
			auxAutor.adicionaLivro(auxLivro);
			// adicionar autor ao livro
			auxLivro.adicionaAutor(auxAutor);

			// buscar infos que faltam do autor
			String nome = auxAutor.getNome();
			// buscar infos que faltam do livro
			String titulo = auxLivro.getTitulo();
			int ano = auxLivro.getAno();

			System.out.printf("5;%d;%s;%s;%s;%d\n", id, nome, isbn, titulo, ano);
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
		StringBuilder autorNames = new StringBuilder();
		for (Autor autor : livroPasso7.getAutores()) {
			if (autorNames.length() > 0) {
				autorNames.append(";");
			}
			autorNames.append(autor.getNome());
		}

		System.out.printf("7;%s;%s\n", isbnPasso7, autorNames.toString());

		String aux10 = entrada.nextLine();
		System.out.println("PASSO 10: " + aux10);
	}

}
