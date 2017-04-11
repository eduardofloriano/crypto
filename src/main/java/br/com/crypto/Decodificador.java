package br.com.crypto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import br.com.crypto.model.PatternAZ;

public class Decodificador {

	public static final char POSICAO_CERTA = 'R';
	public static final char POSICAO_ERRADA = 'U';
	public static final char NAO_EXISTE = 'W';

	private List<String> letrasUsadas = new ArrayList<String>();
	private List<Character> letrasInexistentes = new ArrayList<Character>();
	private List<String> letrasTrocadas = new ArrayList<String>();
	//private char[] letrasCorretas = new char[9];
	private Map<Integer, Character> letrasCorretas = new HashMap<Integer, Character>();
	

	private char[] palavraDecodificada = new char[9];
	
	public Decodificador(){
		
//		BufferedReader br = null;
//		FileReader fr = null;
//		
//		try{
//			
//			fr = new FileReader("F:\\crypto.log");
//			br = new BufferedReader(fr);
//
//			String linha;
//			
//			br = new BufferedReader(fr);
//			while ((linha = br.readLine()) != null) {
//				System.out.println(linha);
//			}
//			
//		}catch(Exception e){
//			System.out.println("Erro ao consultar o log!");
//			e.printStackTrace();
//		}
		
		
	}

	public void decodificar(String original, String codificada) {

		char[] originalList = original.toCharArray();
		char[] codificadaList = codificada.toCharArray();

		for (int i = 0; i < codificadaList.length; i++) {
			//System.out.println("Char: " + codificadaList[i]);

			if (codificadaList[i] == POSICAO_CERTA) palavraDecodificada[i] = codificadaList[i];  //letrasCorretas.put(i, codificadaList[i]);
			if (codificadaList[i] == POSICAO_ERRADA); letrasTrocadas.add(Character.toString(codificadaList[i])) ;
			if (codificadaList[i] == NAO_EXISTE) letrasInexistentes.add(codificadaList[i])  ;

		}
		
		
	}
	
	public String getNovaPalavra(){
		
		char[] novaPalavra = new char[9];
		
		//A NOVA PALAVRA JÁ DEVE CONTER AS LETRAS CORRETAS
		for (int i = 0; i < palavraDecodificada.length; i++) {
			if (palavraDecodificada[i] != '\u0000'){
				novaPalavra[i] = palavraDecodificada[i];
			}
		}
		
		
		//PREENCHE A NOVA PALAVRA
		for(int i = 0; i < novaPalavra.length; i++){
			//SE JA EXISTIR LETRA CORRETA NA NOVA PALAVRA - CONTINUA PRO PROXIMO
			if(novaPalavra[i] != '\u0000' ){
				continue;
			}
			
			Character novaLetra = getLetra();
			novaPalavra[i] = novaLetra;
		}
		
		return String.valueOf(novaPalavra);
	}

	private char getLetra() {
		
//		if(letrasUsadas.size() >= 9){
//			throw new RuntimeException("Letras usadas ultrapassou o limite de 9 characteres!");
//		}
		
		String letra = null;
		int randomNum = ThreadLocalRandom.current().nextInt(1, 61 + 1);
		if (randomNum > 9) {
			letra = getLetraFromPattern(randomNum);
		} else {
			letra = Integer.toString(randomNum);
		}
		
		if(letrasInexistentes.contains(letra)){
			return getLetra();
		}		
		if(letrasUsadas.contains(letra)){
			return getLetra();
		}
		
		letrasUsadas.add(letra);
		return letra.charAt(0);
		
	}

	private String getLetraFromPattern(int num) {
		for (PatternAZ e : PatternAZ.values()) {
			if (e.getValor() == num) {
				return e.toString();
			}
		}
		throw new RuntimeException("Não encontrou o pattern para esse valor!");
	}

	public void log(String valor) {
		try {
			File log = new File("F:\\crypto.log");
			BufferedWriter output = new BufferedWriter(new FileWriter(log));
			output.write(valor);
			output.flush();
			output.close();
		} catch (Exception e) {
			System.out.println("Erro ao escrever no log!");
			e.printStackTrace();
		}
	}

}
