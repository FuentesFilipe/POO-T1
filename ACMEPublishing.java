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
			BufferedReader streamEntrada = new BufferedReader(new FileReader("src/dados.txt"));
			entrada = new Scanner(streamEntrada); // Usa como entrada um arquivo
			PrintStream streamSaida =
					new PrintStream(new File("src/saida.txt"), Charset.forName("UTF-8"));
			System.setOut(streamSaida); // Usa como saida um arquivo
		} catch (Exception e) {
			System.out.println(e);
		}
		entrada.useLocale(Locale.ENGLISH);
	}

	public void executa() {
		while (entrada.hasNextLine()) {
			String id = entrada.nextLine();
			if (id.equals("-1")) {
				break;
			}
			String infoLivro = entrada.nextLine() + "," + entrada.nextLine();
			String[] campos = infoLivro.split(",");
			String nome = campos[0];
			String ano = campos[1];

			// Do something with the book information
			System.out.println("ISBN LIVRO: " + id);
			System.out.println("NOME LIVRO: " + nome);
			System.out.println("ANO DE LANCAMENTO: " + ano);
		}


		while (entrada.hasNextLine()) {
			String id = entrada.nextLine();
			if (id.equals("-1")) {
				break;
			}
			String infoAutores = entrada.nextLine() + "," + entrada.nextLine();
			String[] campos = infoAutores.split(",");
			String nome = campos[0];
			String ano = campos[1];

			// Do something with the book information
			System.out.println("ISBN LIVRO: " + id);
			System.out.println("NOME LIVRO: " + nome);
			System.out.println("ANO DE LANCAMENTO: " + ano);
		}
		while (entrada.hasNextLine()) { // TODO: UPDATE ME WITH ABOVE LOGIC
			String line = entrada.nextLine();
			if (line.equals("-1"))
				break;
			System.out.println("ID AUTOR: " + line); // essa vai ser o ID
			System.out.println("ISBN LIVRO: " + entrada.nextLine()); // proxima linha depois do Id, nome
		}
	}

}
