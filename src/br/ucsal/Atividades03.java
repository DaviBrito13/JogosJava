package br.ucsal;

import java.util.Random;
import java.util.Scanner;

public class Atividades03 {
	
	static boolean cadastrado = false;
	
	static int nome = 0,senha = 1;
	
	static String[] acesso = new String[2];
	
	public static void main(String[] args) {
		
	if(!cadastrado) {
		Cadastro();
		}
	
	Scanner in = new Scanner(System.in);	
		
	int escolha = 0;
	
	limparTela();
	
	System.out.println("Ola "+acesso[nome]+".\nEscolha um jogo dentre as opcoes abaixo:\n\n(1)-JOGO DA FORCA\n\n(2)-CAMPO MINADO\n\n(3)-BATALHA NAVAL\n\n(4)-SAIR DO PROGRAMA\n");	
		
		do {
			escolha = in.nextInt();
			
			if(escolha < 0 || escolha > 4) {
				System.out.println("Voc� deve escolher um n�mero de 1 � 4...");
				System.out.println("\nEscolha um jogo dentre as op��es abaixo:\n\n(1)-JOGO DA FORCA\n\n(2)-CAMPO MINADO\n\n(3)-BATALHA NAVAL\n\n(4)-SAIR DO PROGRAMA\n");
			}
			
		}while(escolha < 0 || escolha > 4);
		
		switch(escolha) {
		case 1:
			limparTela();
			JogoDaForca.main(args);
			break;
		case 2:
			limparTela();
			CampoMinado.main(args);
			break;
		case 3:
			limparTela();
			BatalhaNaval.main(args);
			main(args);
			break;
		case 4:	
			System.out.println("\nFim de execu��o...");
			System.exit(0);
		}
		
		main(args);
		
	}
	
	private static void Cadastro() {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Insira seu nome: ");
		acesso[nome] = in.next();
		System.out.println("Insira sua senha: ");
		acesso[senha] = in.next();
		
		cadastrado = true;
	}

	public static void limparTela() {
		for(int i = 0;i<20;i++) {
		System.out.println("\n");
		}
		
	}
	
	public static void Continuar() {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Insira qualquer coisa para continuar...");
		String nada = in.next();
		limparTela();
		
	}
	
	
}
