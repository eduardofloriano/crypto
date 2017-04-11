package br.com.crypto.service;

import br.com.crypto.Codificador;
import br.com.crypto.Decodificador;

public class ConsultarDesafio {

	private static int HTTP_COD_SUCESSO = 200;
	
	public static void main(String args[]) throws Exception{
		
//		
//		URL url = new URL("https://ac-challenge.herokuapp.com/api/challenge");
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		
//		if (con.getResponseCode() != HTTP_COD_SUCESSO) {
//			System.out.println("Conexao com Erro!");
//		}else{
//			System.out.println("Conexao com Sucesso!");
//		}
//		
//		
//		String theString = IOUtils.toString(con.getInputStream(), "UTF-8"); 
		
//		int randomNum = ThreadLocalRandom.current().nextInt(1, 53 + 1);
//		System.out.println(randomNum);
		
		Decodificador decodificador = new Decodificador();
		String novaChave = decodificador.getNovaPalavra();
		System.out.println("palavra chave: " + novaChave );
		
		decodificador = new Decodificador();		
		Codificador codificador = new Codificador(novaChave);
		
		String palavraNova = null;
		String palavraCodificada = null;
		palavraNova = decodificador.getNovaPalavra();
		palavraCodificada = codificador.processar(palavraNova);
		decodificador.decodificar(palavraNova, palavraCodificada);
		System.out.println("Palavra codificada: " + palavraCodificada);
		
		palavraNova = decodificador.getNovaPalavra();
		palavraCodificada = codificador.processar(palavraNova);
		decodificador.decodificar(palavraNova, palavraCodificada);
		System.out.println("Palavra codificada: " + palavraCodificada);
		
		palavraNova = decodificador.getNovaPalavra();
		palavraCodificada = codificador.processar(palavraNova);
		decodificador.decodificar(palavraNova, palavraCodificada);
		System.out.println("Palavra codificada: " + palavraCodificada);
		
		palavraNova = decodificador.getNovaPalavra();
		palavraCodificada = codificador.processar(palavraNova);
		decodificador.decodificar(palavraNova, palavraCodificada);
		System.out.println("Palavra codificada: " + palavraCodificada);
		
		
		System.out.println("Palavra codificada: " + codificador.processar( novaChave ));
		
//		decodificador.decodificar("Teste", "RWURU");
		
	}
	
}
