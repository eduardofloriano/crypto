package br.com.crypto;

public class Codificador {
	
	private char[] palavraChave;
	
	public Codificador(String palavra){
		
		palavraChave = palavra.toCharArray();
		
	}
	
	public String processar( String palavraRandom ){
		
		System.out.println("Palavra recebida: " + palavraRandom);
		
		char[] palavraRandomArray = palavraRandom.toCharArray();
		char[] palavraCodificada =  new char[palavraRandomArray.length];
		
		
		for(int i = 0; i < palavraRandomArray.length; i++){
			if(palavraRandomArray[i] == palavraChave[i]) palavraCodificada[i] = 'R';
			else if( contemChar(palavraRandomArray[i])) palavraCodificada[i] = 'U';
			else palavraCodificada[i] = 'W';
		}
		
		return String.valueOf(palavraCodificada);
	}
	
	private boolean contemChar(char letra){
		boolean contemChar = false;
		for(int i = 0; i < palavraChave.length; i++){
			if (palavraChave[i] == letra ) contemChar = true;
		}
		return contemChar;
	}
	
	

}
