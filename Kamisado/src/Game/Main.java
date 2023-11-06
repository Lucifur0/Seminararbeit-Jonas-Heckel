
package Game;

import java.util.Scanner;

public class Main {

	public static Tower TWhite [] = new Tower [8];
	public static Tower TBlack [] = new Tower [8];
	static Field Board;
	public static Turn turn;
	static String CTeam;
	static int distance;
	private static Tower next;
	
	public static void main(String[] args){
		startGame();
		Game();
	}

	public static void startGame () {
		Board = new Field ();

		//Team, Color, PosY, PosX
		Tower W1 = new Tower(1,7,7,0);
		Tower W2 = new Tower(1,6,7,1);
		Tower W3 = new Tower(1,5,7,2);
		Tower W4 = new Tower(1,4,7,3);
		Tower W5 = new Tower(1,3,7,4);
		Tower W6 = new Tower(1,2,7,5);
		Tower W7 = new Tower(1,1,7,6);
		Tower W8 = new Tower(1,0,7,7);

		TWhite [0] = W1;
		TWhite [1] = W2;
		TWhite [2] = W3;
		TWhite [3] = W4;
		TWhite [4] = W5;
		TWhite [5] = W6;
		TWhite [6] = W7;
		TWhite [7] = W8;

		//Team, Color, PosY, PosX
		Tower B1 = new Tower(2,0,0,0);
		Tower B2 = new Tower(2,1,0,1);
		Tower B3 = new Tower(2,2,0,2);
		Tower B4 = new Tower(2,3,0,3);
		Tower B5 = new Tower(2,4,0,4);
		Tower B6 = new Tower(2,5,0,5);
		Tower B7 = new Tower(2,6,0,6);
		Tower B8 = new Tower(2,7,0,7);

		TBlack [0] = B1;
		TBlack [1] = B2;
		TBlack [2] = B3;
		TBlack [3] = B4;
		TBlack [4] = B5;
		TBlack [5] = B6;
		TBlack [6] = B7;
		TBlack [7] = B8;
	}

	public static void outAll () { //Debug
		for (int i = 0;i<8;i++) {
			TWhite [i].giveValues();
		}
		for (int i = 0;i<8;i++) {
			TBlack [i].giveValues();
		}
	}

	public static void Game () {
		first ();
		while (turn != null){
			moves ();

		}
	}

	public static void first() {
		Scanner in = new Scanner (System.in);

		System.out.println ("It is currently White`s turn");
		System.out.println("Choose a Tower to move:");
		Tower Tfirst;
		int choice = in.nextInt(); 			//manuel
		if (choice==0) {
			Tfirst=TWhite[0];
		}
		else if (choice==1) {
			Tfirst=TWhite[1];
		}
		else if (choice==2) {
			Tfirst=TWhite[2];
		}
		else if (choice==3) {
			Tfirst=TWhite[3];
		}
		else if (choice==4) {
			Tfirst=TWhite[4];
		}
		else if (choice==5) {
			Tfirst=TWhite[5];
		}
		else if (choice==6) {
			Tfirst=TWhite[6];
		}
		else{
			Tfirst=TWhite[7];
		}
		turn = new Turn (Tfirst.giveposy(), Tfirst.giveposx(), Tfirst);
		System.out.println("you chose "+turn.current.givecolorname());
		moves ();

	}

	public static void moves () {
		Scanner in = new Scanner (System.in);

		int NMax;
		int direktion;
		do {
			int Max = turn.MaxM();
			if (Max == 0) {
				System.out.println("This tower is blocked.");
				distance = 0;
				direktion = 0;
				break;

			}
			System.out.println("What way do you want to move?(1= left; 2= streight;3= right)");
			direktion = in.nextInt();
			System.out.println("How manny fields do you want to move");
			if (direktion == 1) { // left
				NMax = Max/100;
			}
			else if (direktion == 3){ //right
				NMax = Max/10;
				NMax = NMax%10;
			}
			else {
				NMax = Max%10; //straight
			}
			System.out.println("This Tower can Move "+NMax+ " fields.");
			if (NMax != 0) {
				do {
					distance = in.nextInt();
					if (distance > NMax) {
						System.out.println("This tower cant move this far, trie again.");
					}
					else if (distance == 0) {
						System.out.println("The Tower has to move at least 1 Fields");
					}
				} while (distance> NMax || distance == 0 );
			}
			else {
				System.out.println("Sry but this tower can't move in this direction, chose again.");
			}

		} while (NMax==0 );
		turn.TeamMove(direktion, distance);
		System.out.println(turn.current.givecolorname()+" moves "+ distance +" fields");
		next = turn.TowernextTurn();
		if (next != null){
			turn = new Turn (next.Posy, next.Posx, next);			
		}
		else {
			turn = null;
		}
	}

	public int [] towerposWhite (){
		int [] pos = new int [8];
		for (int i = 0; i<8;i++) {
			pos [i] = TWhite[i].Posx*10+TWhite[i].Posy;
		}
		return pos;
	}
	
	public int [] towerposBlack (){
		int [] pos = new int [8];
		for (int i = 0; i<8;i++) {
			pos [i] = TBlack[i].Posx*10+TBlack[i].Posy;
		}
		return pos;
	}

	
}
