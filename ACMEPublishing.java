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
		// PASSO 1: CADASTRAR LIVROS
		while (entrada.hasNextLine()) {
			String id = entrada.nextLine(); // isbn lido do livro
			if (id.equals("-1")) { // sempre que ler "-1", ir para prox funcionalidade
				break;
			}
			// proximas 2 linhas depois do Id vao ser Nome e Ano respectivamente
			String infoLivro = entrada.nextLine() + "," + entrada.nextLine();
			String[] campos = infoLivro.split(",");
			String nome = campos[0];
			String ano = campos[1];

			// testes no arquivo de saido
			System.out.println("ISBN LIVRO: " + id);
			System.out.println("NOME LIVRO: " + nome);
			System.out.println("ANO DE LANCAMENTO: " + ano);
		}

		// PASSO 3: CADASTRAR AUTORES
		while (entrada.hasNextLine()) {
			String id = entrada.nextLine(); // Id lido do Autor
			if (id.equals("-1")) {
				break;
			}
			// proximas 2 linhas depois do Id vao ser Nome e isbn respectivamente
			String infoAutores = entrada.nextLine() + "," + entrada.nextLine();
			String[] campos = infoAutores.split(",");
			String nome = campos[0];
			String isbn = campos[1];

			// Do something with the book information
			System.out.println("ID AUTOR: " + id);
			System.out.println("NOME AUTOR: " + nome);
			System.out.println("ISBN LIVRO: " + isbn);
		}
		while (entrada.hasNextLine()) { // TODO: UPDATE ME WITH ABOVE LOGIC
			String line = entrada.nextLine();
			if (line.equals("-1"))
				break;

		}
	}

}
