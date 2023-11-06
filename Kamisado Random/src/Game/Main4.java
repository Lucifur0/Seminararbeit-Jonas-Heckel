
package Game;

import java.util.Scanner;

public class Main4 {

	public static Tower TWhite [] = new Tower [8];
	public static Tower TBlack [] = new Tower [8];
	static Field Board;
	public static Turn turn;
	static String CTeam;
	static int distance;
	private static Tower next;
	static int count;
	static int countW;
	static int countBr;
	static int countGr;
	static int countR;
	static int countY;
	static int countPi;
	static int countPu;
	static int countBl;
	static int countO;

	
	public static void main(String[] args){
		countW = 0;
		int n = 10;
		for (int i = 0;i<n;i++) {
			count = 0;
			startGame();
			Game();	
		}
		System.out.println();
		System.out.println("White wonn "+ countW+" times");
		System.out.println("Black wonn "+ (n-countW)+" times");
		System.out.println("Brown wonn "+ countBr+" times");
		System.out.println("Green wonn "+ countGr+" times");
		System.out.println("Red wonn "+ countR+" times");
		System.out.println("Yellow wonn "+ countY+" times");
		System.out.println("Pink wonn "+ countPi+" times");
		System.out.println("Purple wonn "+ countPu+" times");
		System.out.println("Blue wonn "+ countBl+" times");
		System.out.println("Orange wonn "+ countO+" times");
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

	public static void Game () {
		first ();
		while (turn != null){
			moves ();

		}
	}

	public static void first() {

		//System.out.println ("It is currently White`s turn");
		//System.out.println("Choose a Tower to move:");
		Tower Tfirst;
		int choice = (int)(Math.random()*8);
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
		//System.out.println("you chose "+turn.current.givecolorname());
		moves ();

	}

	public static void moves () {
		//Scanner in = new Scanner (System.in);

		int NMax;
		int direktion = 0;
		do {
			int Max = turn.MaxM();
			if (Max == 0) {
//				System.out.println("This tower is blocked.");
//				Board.outField();
				distance = 0;
				direktion = 0;
				break;

			}
			else if (Max > 1000) {
				direktion = 1000;
				break;
			}
			do {
				int n = 1;
				int m = 3;
				if (Max/100 == 0) { //left
					n++;
				}
				else if ((Max/10)%10==0) {
					m--;
				}
				direktion = (int)(Math.random()*m)+n;
				//System.out.println(direktion);
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
				//System.out.println("This Tower can Move "+NMax+ " fields.");
				distance = (int)(Math.random()*(NMax+1));
				//System.out.println(distance);
			} while (distance<= 0);
		} while (NMax==0 );
		if (direktion == 1000) {
			System.out.println(turn.current.giveTeamname()+" "+turn.current.givecolorname()+" won");
			if (turn.current.Team == 1) {
				countW++;
			}
			colorWon();
			//Board.outField();
			turn = null;
		}
		else if (count == 100) {
			System.out.print("oud of bounds");
			turn = null;
		}
		else {
			//Board.outField();
			turn.TeamMove(direktion, distance);
			//System.out.println(turn.current.givecolorname()+" moves "+ distance +" fields");
			count ++;
			//System.out.println(count);
			next = turn.TowernextTurn();
			turn = new Turn (next.Posy, next.Posx, next);			
		}
	}
	
	private static void colorWon() {
		if (turn.current.Color==7) {
			countBr++;
		}
		else if(turn.current.Color==6) {
			countGr++;
		}
		else if(turn.current.Color==5) {
			countR++;
		}
		else if(turn.current.Color==4) {
			countY++;
		}
		else if(turn.current.Color==3) {
			countPi++;
		}
		else if(turn.current.Color==2) {
			countPu++;
		}
		else if(turn.current.Color==1) {
			countBl++;
		}
		else if(turn.current.Color==0) {
			countO++;
		}
	}

}
