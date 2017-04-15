package br.com.crypto.service;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import br.com.crypto.Codificador;
import br.com.crypto.Decodificador;

public class ConsultarDesafio {

	private static int HTTP_COD_SUCESSO = 200;
	private static int HTTP_COD_SUCESSO_PARCIAL = 206;

	
	public static void main(String args[]) throws Exception {

//		desenvolvimento();
		
		
		Decodificador decodificador = new Decodificador(35);
		String palavraNova = null;
		String palavraCodificada = null;
		
		int total = 0;

		while (!decodificador.palavraEstaDecodificada) {

			palavraNova = decodificador.getNovaPalavra();
			System.out.println("Palavra enviada: "+ palavraNova );
			palavraCodificada = consultarServico(palavraNova);
			decodificador.decodificar(palavraNova, palavraCodificada);
			System.out.println("Palavra codificada: " + palavraCodificada );
			total++;
			System.out.println("Total de requests: " + total + "\n");
		}
		
	}

	public static void desenvolvimento() {
		Decodificador decodificador = new Decodificador(35);
		String novaChave = decodificador.getNovaPalavra();
		System.out.println("palavra chave: " + novaChave);

		decodificador = new Decodificador(35);
		Codificador codificador = new Codificador(novaChave);

		String palavraNova = null;
		String palavraCodificada = null;

		int total = 0;

		while (!decodificador.palavraEstaDecodificada) {

			palavraNova = decodificador.getNovaPalavra();
			palavraCodificada = codificador.processar(palavraNova);
			decodificador.decodificar(palavraNova, palavraCodificada);
			System.out.println("Palavra codificada: " + palavraCodificada
					+ "\n");
			total++;
		}

		System.out.println("Total de requests: " + total);
	}

	private static String consultarServico(String palavra) {
		
		String theString = null;
		try {

			URL url = new URL("https://ac-challenge.herokuapp.com/api/challenge?coder=eduardofloriano@outlook.com&test=false&challenge=" + palavra);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			if (con.getResponseCode() != HTTP_COD_SUCESSO ) {
				System.out.println("Conexao com Sucesso!");
			} else if (con.getResponseCode() != HTTP_COD_SUCESSO_PARCIAL) {
				System.out.println("Conexao com Sucesso!");
			} else {
				System.out.println("Conexao com Erro!");
			}

			theString = IOUtils.toString(con.getInputStream(), "UTF-8");
			System.out.println("Resposta do servico: " + theString);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro no processo de producao! "+ e.getMessage());
			e.printStackTrace();
		}
		
		return theString;
	}
	
	
	
}
