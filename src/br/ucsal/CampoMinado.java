package br.ucsal;

import java.util.Random;
import java.util.Scanner;

public class CampoMinado {
	
	
	static int [][][] map = new int[16][16][2];
	
	
	final static int mina = -1;

	public static void main(String [] args) {
		
	
		gerarMinas();

		do {
	
			Atividades03.limparTela();
			
			
			desenharMapa();
   
			jogar();
    
		}while(!acabou());

		desenharMapa();
		
		resultado();

		Atividades03.Continuar();

		System.out.println("");

	}

	private static void resultado() {

		boolean perdeu = false;
		
		
		for(int y = 0;y<16;y++) {
			for(int x = 0;x<16;x++) {
				if(map[x][y][1] == 1 && map[x][y][0] == mina) {
					System.out.println("\n            Você Perdeu!");
					perdeu = true;
				}
			}
		}
		
		
		if(!perdeu) {
			System.out.println("\n            Você Venceu!!!");
		}

	}

	private static boolean acabou() {

		
		for(int y = 0;y<16;y++) {
			for(int x = 0;x<16;x++) {
				if(map[x][y][1] == 1 && map[x][y][0] == mina) {
					return true;
				}
			}
		}
		
		for(int y = 0;y<16;y++) {
			for(int x = 0;x<16;x++) {
				if(map[x][y][0] != mina && map[x][y][1] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	private static void jogar() {
		int x,y;

		Scanner in = new Scanner(System.in);

		do {
			System.out.println("Informe a letra correspondente á coluna desejada:");
			
			
			x = (int)in.next().toUpperCase().charAt(0)-65;
			if(x<0 || x>15) {
				System.out.println("Coluna inválida :(");
			}
		}while(x<0 || x>15);

		do {
			System.out.println("Informe a letra correspondente á fileira desejada:");
			y = (int)in.next().toUpperCase().charAt(0)-65;
			if(y<0 || y>15) {
				System.out.println("Fileira inválida :(");
			}
		}while(y<0 || y>15);

		
		map[x][y][1] = 1;

	}

	private static void desenharMapa() {

		System.out.println("+----------<CAMPO MINADO>-----------+");

		boolean repetir = true;

		
		for(int i = 0;i<16;i++) {
			for(int j = 0;j<16;j++) {
				if(map[i][j][0] == 0 && map[i][j][1] == 1) {
					
					
					abrirEmVolta(i,j);
				}
			}
		}


		System.out.print("| + ");
		for(int i = 0;i<16;i++) {
			System.out.print(Character.toChars(i+65));
			System.out.print(" ");
		}
		System.out.print("|\n");
		for(int y = 0;y<16;y++) {
			System.out.print("| ");
			System.out.print(Character.toChars(y+65));
			System.out.print(" ");
			for(int x = 0;x<16;x++) {

				if(map[x][y][1] == 0) {
					System.out.print("■ ");
				}else {
					if(map[x][y][0] == 0) {
						System.out.print("  ");
					}	
					else if(map[x][y][0] == mina) {
						System.out.print("X ");	
					}
					else {
						System.out.print(map[x][y][0]+" ");
					}
				}

			}

			System.out.print("|\n");

		}

		System.out.println("+-----------------------------------+");

	}

	private static void abrirEmVolta(int i, int j) {
		for(int x = -1;x<2;x++) {
			for(int y = -1;y<2;y++) {
				if(i+x>=0 && i+x<16 && j+y>=0 && j+y<16) {
					if(map[i+x][j+y][0] == 0 && map[i+x][j+y][1] == 0) {
						map[i][j][1] = 1;
						abrirEmVolta(i+x,j+y);
					}
					map[i+x][j+y][1] = 1;
				}
			}
		}
	}

	private static void gerarMinas() {

		Random rand = new Random();

		for(int z = 0;z<20;z++) {

			int x = rand.nextInt(16),y = rand.nextInt(16);

			if(map[x][y][0] != mina) {
				map[x][y][0] = mina;
				
				for(int i = -1;i<2;i++) {
					for(int j = -1;j<2;j++) {
						if(x+i < 16 && y+j < 16 && x+i > -1 && y+j > -1) {
							if(map[x+i][y+j][0] != mina) {
								map[x+i][y+j][0]++;
							} 
						}
					}
				}

			}else {
				z--;
			}
		}

	}
}
