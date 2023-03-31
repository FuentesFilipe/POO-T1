import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.sql.SQLOutput;
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

			// testes no arquivo de saido TODO: IMPLEMENT TASK
//			System.out.println("ISBN LIVRO: " + isbn);
//			System.out.println("NOME LIVRO: " + nome);
//			System.out.println("ANO DE LANCAMENTO: " + ano);
			System.out.printf("1;%s;%s;%d\n", isbn, nome, ano);
		}

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

			// Do something with the book information TODO: IMPLEMENT TASK
//			System.out.println("ID AUTOR: " + id);
//			System.out.println("NOME AUTOR: " + nome);
//			System.out.println("ISBN LIVRO: " + isbn);
			System.out.printf("3;%d;%s;%s\n", id, nome, isbn);
		}

		// PASSO 5: ADICIONAR LIVRO A UM AUTOR
		while (entrada.hasNextLine()) { // TODO: UPDATE ME WITH ABOVE LOGIC
			String id = entrada.nextLine(); // Id lido do autor
			if (id.equals("-1"))
				break;
			String isbn = entrada.nextLine();

			// testing TODO: IMPLEMENT TASK
			System.out.println("ID AUTOR PRA ADD LIVRO: " + id);
			System.out.println("ISBN DO LIVRO: " + isbn);
		}
		String aux6 = entrada.nextLine();
		String aux7 = entrada.nextLine();
		String aux10 = entrada.nextLine();

		System.out.println("PASSO 6: " + aux6);
		System.out.println("PASSO 7: " + aux7);
		System.out.println("PASSO 10: " + aux10);
	}

}
