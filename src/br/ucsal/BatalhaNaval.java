package br.ucsal;

import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {
	
	
	static int [][][] tab = new int [10][10][2];
	
	static int [][][] editor = new int [10][10][2];

	public static void main(String [] args) {
		
		
		int nJogadores = definirJogadores();
		
		
		definirBarcos(nJogadores);

		int jog = 0;
		
		Atividades03.limparTela();
		System.out.println("\n          Jogador "+(jog+1)+" - Ataque!\n");

		do {

			if(nJogadores == 1 && jog == 0) {
				desenharTabuleiro(jog);
			}

			if(nJogadores == 2  ) {
				desenharTabuleiro(jog);
			}
			
			
			jog = Jogar(jog==0?1:0,nJogadores);
			
			
		}while (!acabou());
		
		Atividades03.limparTela();

		
		Resultado(jog);
		
		Atividades03.Continuar();
		


	}

	private static int definirJogadores() {
		
		Scanner in = new Scanner(System.in);

		System.out.println("Escolha o número de jogadores: 1 ou 2");
		int n = 0;
		do {

			n = in.nextInt();

			if(n != 1 && n != 2) {
				System.out.println("Escolha 1 ou 2...");
			}

		}while(n != 1 && n != 2);

		return n;
	}

	private static void Resultado(int jog) {
		
		System.out.println("\n\t\tJogador "+(jog+1)+" venceu !!!\n");
		
		desenharTabuleiro(jog==0?1:0);
		
		System.out.println("\n");

	}

	private static boolean acabou() {

		boolean b0 = false,b1 = false;
		
		for(int i = 0;i<tab.length;i++) {
			for(int j = 0;j<tab.length;j++) {
				if(tab[i][j][0] > 0) {
					b0 = true;
					break;
				}
			}
		}
		
		for(int i = 0;i<tab.length;i++) {
			for(int j = 0;j<tab.length;j++) {
				if(tab[i][j][1] > 0 ) {
					b1 = true;
					break;
				}
			}
		}

		if(b1 && b0) {
			return false;
		}else {
			return true;
		}
	}

	private static int Jogar(int jog,int nJogadores) {

		if(nJogadores == 2 || (nJogadores == 1 && jog == 1)) {

			Scanner in = new Scanner(System.in);
			String jogada;
			int jx;
			char l,jy;

			System.out.println("Digite uma coordenada, Ex.(a1,d4,f3,j0)");

			do {
				do {
				jogada = in.next().toUpperCase();
				if(jogada.length()<2) {
					System.out.println("Você deve informar duas coordenadas...\n");
				}
				}while(jogada.length()<2);
				l = jogada.charAt(0);

				jy = jogada.charAt(1);

				jx = (int)l;

				if(jogada.length()<2 || jx-65<0 || jx-65>9 ||jy-'0'<0 ||jy-'0'> 9) {
					System.out.println("Jogada inválida... Tente novamente\n");
				}

			}while(jogada.length()<2 || jx-65<0 || jx-65>9 ||jy-'0'<0 ||jy-'0'> 9);



			if(tab[jx-65][jy-'0'][jog] > 0) {
				tab[jx-65][jy-'0'][jog] = -1;
				Atividades03.limparTela();
				System.out.println("Você acertou uma unidade inimiga! Tente de novo...");
				if(jog == 0) {
					jog = 1;
				}else{
					jog = 0;
				}
			}else {
				System.out.println("Água! você está ruim de mira...");
				Atividades03.Continuar();
				System.out.println("\n          Jogador "+(jog+1)+" - Ataque!\n");
			}
			
		}else {
			Random rand = new Random();
			
			int x,y;
			
			x = rand.nextInt(9);
			y = rand.nextInt(9);
			
			if(tab[x][y][jog] > 0) {
				tab[x][y][jog] = -1;
				
				if(jog == 0) {
					jog = 1;
				}else{
					jog = 0;
				}
			}
		}
		
		return jog;

	}

	private static void definirBarcos(int nJogadores) {

		if(nJogadores == 2) {
			BotarBarcos(0);
			BotarBarcos(1);
		}else {
			BotarBarcos(0);
			BarcosAleatorios(1);
		}

	}

	private static void BarcosAleatorios(int jog) {

		Random rand = new Random();
		int x,y;

		for(int i = 5;i>1;i--) {
			
			int [][]barco = new int[i][2];

			do {
				x = rand.nextInt(9-i-1);
				y = rand.nextInt(9);
				
				for(int j = 0;j<i;j++) {
					barco[j][0] = x+j;
				}
				for(int j = 0;j<i;j++) {
					barco[j][1] = y;
				}

				if(Sobrepondo(barco,jog)) {

					y = rand.nextInt(9-i)+i;

					for(int j = 0;j<i;j++) {
						barco[j][1] = y+j;
					}
					for(int j = 0;j<i;j++) {
						barco[j][0] = x;
					}
				}	
			}while(Sobrepondo(barco,jog));

			ColocarBarco(barco,jog);
	
		}
	}

	private static void BotarBarcos(int jog) {

		Scanner in = new Scanner(System.in);
		
		for(int i = 5;i>1;i--) {
			String comando = "";
			
			int [][] barco = new int [i][2];
			
			int [][] Ultimobarco = new int [i][2];

			
			for(int j = 0;j<barco.length;j++) {
				barco[j][0] = 3+j; 
			}
			for(int j = 0;j<barco.length;j++) {
				barco[j][1] = 5;
			}
			
			
			EditarBarco(barco,jog);

			do {
				Atividades03.limparTela();
				
				System.out.println("\n     Jogador "+(jog+1)+" - Coloque seus barcos\n");

				
				desenharEditor(jog);

				System.out.println("\n    ↑\n    W       Aperte 'C' para confirmar\n← A + D →\n    S      Aperte 'G' para girar o barco\n    ↓");

				
				editor = new int [10][10][2];

				comando = in.next();

				comando = comando.toLowerCase();

				
				Ultimobarco = new int [i][2];
				
				
				for(int b = 0;b<barco.length;b++) {
					Ultimobarco[b][0] = barco[b][0];
					Ultimobarco[b][1] = barco[b][1];
				}
				
				
				barco = RodarComando(barco,comando);
				
				
				if(checar(barco,jog) == false) {

					for(int b = 0;b<barco.length;b++) {
						barco[b][0] = Ultimobarco[b][0];
						barco[b][1] = Ultimobarco[b][1];
					}

					System.out.println("Movimento Inválido...");
				}

				EditarBarco(barco,jog);
				
				
			}while(comando.charAt(0) != 'c' || Sobrepondo(barco,jog));
			
			
			ColocarBarco(barco,jog);

			editor = new int [10][10][2];

		}

	}

	private static boolean Sobrepondo(int [][]barco,int jog) {

		for(int i = 0;i<barco.length;i++) {
			if(tab[barco[i][0]][barco[i][1]][jog] != 0) {
				return true;
			}
		}

		return false;
	}

	private static void desenharEditor(int jog) {

		int [][][] editorx = new int [10][10][2];
		
		
		for(int i = 0;i<2;i++) {
			for(int j = 0;j<tab.length;j++) {
				for(int k = 0;k<tab.length;k++) {
					editorx[k][j][i] = tab[k][j][i];
				}
			}
		}
		
		
		for(int i = 0;i<2;i++) {
			for(int j = 0;j<tab.length;j++) {
				for(int k = 0;k<tab.length;k++) {
					if(editor[k][j][i] != 0) {
						editorx[k][j][i] = editor[k][j][i];
					}
				}
			}
		}
		System.out.println("\t     BATALHA NAVAL");
		System.out.println("\t_______________________");	
		System.out.println("\t|+|A|B|C|D|E|F|G|H|I|J|");
		for(int y = 0;y<editorx.length;y++) {
			System.out.print("\t|"+y);
			for(int x = 0;x<editorx.length;x++) {

				switch (editorx[x][y][jog]) {
				case 0:
					System.out.print("≈≈");
					break;
				case 1:
					System.out.print("██");
					break;
				case 2:
					System.out.print("░░");
					break;
				case 3:
					System.out.print("╠╣");
					break;
				case 4:
					System.out.print("╚╝");
					break;	
				default:	
					System.out.print("  ");

				} 

			}
			System.out.print("|\n");
		}

		System.out.println("\t¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

	}

	private static void EditarBarco(int[][] barco, int jog) {
		for(int i = 0;i<barco.length;i++) {
			editor[barco[i][0]] [barco[i][1]] [jog] = 2;
		}

	}

	private static void ColocarBarco(int[][] barco,int jog) {

		for(int i = 0;i<barco.length;i++) {
			tab[barco[i][0]] [barco[i][1]] [jog] = 1;
		}

	}

	private static boolean checar(int[][] barco, int jog) {

		for(int i = 0;i<barco.length;i++) {

			if(barco[i][0] > tab.length-1 || barco[i][0] < 0 || barco[i][1] > tab.length-1 || barco[i][1] < 0 ) {
				return false;
			}
		}
		return true;
	}

	private static int[][] RodarComando(int[][] barco, String comando) {

		switch(comando) {

		case "d":
			for(int i = 0;i<barco.length;i++) {
				barco[i][0]++;
			}
			break;
		case "w":
			for(int i = 0;i<barco.length;i++) {
				barco[i][1]--;
			}
			break;
		case "a":
			for(int i = 0;i<barco.length;i++) {
				barco[i][0]--;
			}
			break;
		case "s":
			for(int i = 0;i<barco.length;i++) {
				barco[i][1]++;
			}
			break;
		case "g":
			if(barco[0][0] != barco[1][0]){
				for(int i = 0;i<barco.length;i++) {
					barco[i][0] = barco[0][0];
				}
				for(int i = 0;i<barco.length;i++) {
					barco[i][1] = barco[0][1]-i;
				}
			}else {
				for(int i = 0;i<barco.length;i++) {
					barco[i][1] = barco[0][1];
				}
				for(int i = 0;i<barco.length;i++) {
					barco[i][0] = barco[0][0]+i;
				}
			}
			break;	
		}

		return barco;
	}

	private static void desenharTabuleiro(int jog) {
		System.out.println("\t     BATALHA NAVAL");
		System.out.println("\t_______________________");	
		System.out.println("\t|+|A|B|C|D|E|F|G|H|I|J|");
		for(int y = 0;y<tab.length;y++) {
			System.out.print("\t|"+y);
			for(int x = 0;x<tab.length;x++) {

				switch (tab[x][y][jog]) {
				case -1:
					System.out.print("##");
					break;
				case 0:
					System.out.print("≈≈");
					break;
				case 1:
					System.out.print("██");
					break;
				case 2:
					System.out.print("░░");
					break;
				case 3:
					System.out.print("╠╣");
					break;
				case 4:
					System.out.print("╚╝");
					break;	
				default:	
					System.out.print("  ");

				} 

			}
			System.out.print("|\n");
		}

		System.out.println("\t¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

	}

}
