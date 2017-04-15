package br.com.crypto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.crypto.model.PatternAZ;

public class Decodificador {

	public static final char POSICAO_CERTA = 'R';
	public static final char POSICAO_ERRADA = 'U';
	public static final char NAO_EXISTE = 'W';

	private List<Character> letrasCorretas = new ArrayList<Character>();
	private Map<Integer, Character> letrasTrocadas = new HashMap<Integer, Character>();
	private ArrayList<String> todasLetras = new ArrayList<String>();
	
	private int tamanhoPalavra;
	private char[] palavraDecodificada;
	public boolean palavraEstaDecodificada = false;
	
	
	public Decodificador(int tamanhoPalavra){
		
		this.tamanhoPalavra = tamanhoPalavra;
		palavraDecodificada = new char[tamanhoPalavra];
		//PREENCHE A LISTA COM TODAS AS LETRAS
		for(int i = 0; i<=63; i++){
			String letra = null;
			if (i > 9 && i < 62) {
				letra = getLetraFromPattern(i);
			} else if( i == 62){ 
				letra = "_";
			} else if ( i == 63) {
				letra = "=";
			}else {
				letra = Integer.toString(i);
			}
			todasLetras.add(letra);
		}
		//EMBARALHA A LISTA
		Collections.shuffle(todasLetras);
	}

	public void decodificar(String original, String codificada) {

		char[] originalList = original.toCharArray();
		char[] codificadaList = codificada.toCharArray();

		for (int i = 0; i < codificadaList.length; i++) {

			if (codificadaList[i] == POSICAO_CERTA){
				letrasCorretas.add(originalList[i]);
				palavraDecodificada[i] = originalList[i];
			}
			if (codificadaList[i] == POSICAO_ERRADA){
				if(!letrasCorretas.contains(originalList[i])){
					letrasTrocadas.put(i, originalList[i]);
				}
				
			}
		}
		
		this.palavraEstaDecodificada = verificaPalavraDecodificada(codificadaList);
		
	}
	
	public boolean verificaPalavraDecodificada(char[] palavraDecodificada ){
		for (int i = 0; i < palavraDecodificada.length; i++) {
			if(palavraDecodificada[i] != POSICAO_CERTA) return false;
		}
		return true;
	}
	
	public String getNovaPalavra(){
		
		char[] novaPalavra = new char[tamanhoPalavra];
		
		//A NOVA PALAVRA NAO DEVE CONTER AS LETRAS CORRETAS
		for (int i = 0; i < palavraDecodificada.length; i++) {
			if (palavraDecodificada[i] != '\u0000'){
				novaPalavra[i] = palavraDecodificada[i];
			}
		}
		
		List<Integer> letrasTrocadasRemove = new ArrayList<Integer>();
		
		//AS LETRAS COM POSICAO ERRADA ANDAM NA FILA
		for (Map.Entry<Integer, Character> entry : letrasTrocadas.entrySet()){
			
			//PROXIMA POSICAO
			Integer proximaPosicao = entry.getKey() + 1;
			
			//SE A PROXIMA POSICAO FOR >= QUE A ULTIMA, VOLTA PRO COMECO
			if( proximaPosicao >= novaPalavra.length -1 ){
				proximaPosicao = 0;
			}
			
			//SE A PROXIMA POSICAO ESTIVER PREENCHIDA, MUDA PARA PROXIMA
			while(novaPalavra[proximaPosicao] != '\u0000'){
				proximaPosicao ++;
				if( proximaPosicao > novaPalavra.length-1 ){
					proximaPosicao = 0;
				}
			}

			//PREENCHE A NOVA PALAVRA
			novaPalavra[proximaPosicao] = entry.getValue();
			
			//REMOVE DA LISTA DAS PALAVRAS TROCADAS
			letrasTrocadasRemove.add(entry.getKey());
		}
		
		//REMOVE DA LISTA DAS LETRAS TROCADAS
		for(Integer i : letrasTrocadasRemove){
			letrasTrocadas.remove(i);
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
	
		String letra = null;
		Collections.shuffle(todasLetras);
		letra = todasLetras.get(0);
		
		if(letrasCorretas.contains(letra)){
			return getLetra();
		}
				
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

}
