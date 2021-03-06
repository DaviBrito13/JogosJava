package br.ucsal;

import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {

	public static void main(String[] args) {

		jogoDaForca();	

	}

	private static void jogoDaForca() {

		
		String [] linhas = {"|         ","|         ","|         ","|         "};
		
		
		String palavra = definirPalavra();
		
		
		String tentativas = "";

		
		int erros = 0;

		do{
			
			Atividades03.limparTela();
			
			
			desenharjogo(erros,linhas,palavra,tentativas);
			
			
			char letra = jogada();
			
			
			while(conferir(letra,tentativas) == false) {
				
				letra = jogada();

			}
			
			
			tentativas += letra;
			
			
			erros = contador(palavra,tentativas);
			
			
		}while(acabou(erros,palavra,tentativas) ==  false); 
		
		Atividades03.limparTela();
		
		desenharjogo(erros,linhas,palavra,tentativas);
		
		
		resultado( palavra, tentativas, erros);
		
		Atividades03.Continuar();
		
		System.out.println("");

	}

	private static boolean acabou(int erros,String palavra,String tentativas) {
		
		int acertos = 0;
		
		for(int i = 0;i<palavra.length();i++) {
			
			boolean contem = false;
			
			
			for(int j = 0;j<tentativas.length();j++) {
				if(tentativas.charAt(j) == palavra.charAt(i)) {
					contem = true;
					break;
				}
			}
			
			
			if(contem) {
				acertos++;
			}
			
		}
		 
		if(acertos == palavra.length()) {
			return true;
		}
		
		 
		if(erros == 6) {
			return true;
		}
		
		return false;
		
	}

	private static void resultado(String palavra,String tentativas,int erros) {
		
		if(erros == 6) {
			System.out.println("\nVocê Perdeu...");
		}else {
			System.out.println("\nParabéns, você venceu!");
		}

	}
	
	private static int contador (String palavra,String tentativas) {
		
		int erros = 0;
		
		for(int i = 0;i<tentativas.length();i++) {
			
			boolean erro = true;
			
			for(int j = 0;j<palavra.length();j++) {

				if(tentativas.charAt(i) == palavra.charAt(j)) {
					erro = false;
				}
			}
			
			if(erro) {
				erros++;
			}
			
		}
		
		return erros;
		
	}
	
	private static boolean conferir(char jogada,String tentativas) {
		
		for(int i = 0;i<tentativas.length();i++) {
			
			if(jogada == tentativas.charAt(i)) {
				System.out.println("\nVocê já informou essa letra anteriormente, tente outra...");
				return false;
			}
			
		}	
			
		return true;
	}

	private static char jogada() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\nInforme uma letra:");
		char j = scan.next().toLowerCase().charAt(0);
		return j;

	}

	private static void desenharjogo(int erros,String [] linhas,String palavra,String tentativas) {

		System.out.println("JOGO DA FORCA");

		
		desenharBoneco(erros,linhas); 

		
		desenharPalavra(palavra,tentativas);
	}

	private static void desenharPalavra(String palavra,String tentativas) {

		System.out.print(" "+"\n");

		String [] linha = new String[palavra.length()];
		
		
		for(int i = 0;i<palavra.length();i++) {	

			linha[i] = "_ ";

		}

		for(int i = 0;i<palavra.length();i++) {

			for(int j = 0;j<tentativas.length();j++) {

				if(tentativas.charAt(j) == palavra.charAt(i)) {
					linha[i] = (palavra.charAt(i)+" ").toUpperCase();
				}
			}
		}

		for(int i = 0;i<palavra.length();i++) {	

			System.out.print(linha[i]);

		}
		System.out.println("\n");
	}

	private static void desenharBoneco(int erros,String []linhas) {

		System.out.println("--------  ");
		System.out.println("|      |  ");

		switch (erros) {
		case 5:case 6:
			if(erros == 5) {
				linhas[2] = "|     /   ";
			}else {
				linhas[2] = "|     / \\";
			}
		case 4:case 3:case 2:
			if(erros >= 4) {
				linhas[1] = "|     /|\\";
			}else if(erros == 3) {
				linhas[1] = "|     /|  ";
			}else{
				linhas[1] = "|      |  ";
			}
		case 1:
			linhas[0] = "|      ☻  ";
		}

		
		System.out.println(linhas[0]);
		System.out.println(linhas[1]);
		System.out.println(linhas[2]);
	}

	public static String definirPalavra() {

		String[] palavras = {"azulado","amarelo","java","astronauta","fernando","paralelepipedo","papibaquigrafo","otorrinolaringologista"};

		Random rand = new Random();

		return palavras[rand.nextInt(palavras.length)];

	}

}
