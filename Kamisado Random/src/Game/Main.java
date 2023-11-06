
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
	private static long n;
	private static long x;
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
		n = 2000000000;
		x = n;
		for (long i = 0;i<n;i++) {
			count=0;
			Board = new Field();
			startGame();
			Game();
		}
		outWon();
	}

	public static void Game () {

		first ();
		while (turn != null){
			moves ();
		}
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

	public static void first() {

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
		moves ();

	}

	public static void moves () {
		int NMax;
		int direktion = 0;
		int Max = turn.MaxM();
		
		if (Max == 0) {
			distance = 0;
			direktion = 0;

		}
		else if (Max >= 1000) {
			direktion = 1000;
		}
		if (Max != 0 && Max <1000) {
			int n = 1;
			int m = 3;
			
			if (Max/100 == 0) { //can't go left
				n++;
				m--;
			}
			if (((Max/10)%10)==0) { //can't go right
				m--;
			}
			
			direktion = (int)(Math.random()*m)+n;

			if (direktion == 1) { // left
				NMax = Max/100;
			}
			else if (direktion == 3){ //right
				NMax = ((Max/10)%10);
			}
			else {
				NMax = Max%10; //straight
			}
			distance = (int)(Math.random()*(NMax))+1; 
		}
		if (direktion == 1000) {
			//System.out.println(turn.current.giveTeamname()+" "+turn.current.givecolorname()+" won");
			if (turn.current.Team == 1) {
				countW++;
			}
			colorWon();
			//Board.outField();
			turn = null;
		}
		else if (count == 100) {
//			SBoard.outField();
//			System.out.println("oud of bounds");
			n++;
//			System.out.println(n);
			turn = null;
		}
		else {
			turn.TeamMove(direktion, distance);
//			System.out.println(turn.current.givecolorname()+" moves "+ distance +" fields");
			count ++;
			next = turn.TowernextTurn();
			turn = new Turn (next.Posy, next.Posx, next);
//			Board.outField();
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

	public static void outAll () { //Debug
		for (int i = 0;i<8;i++) {
			TWhite [i].giveValues();
		}
		for (int i = 0;i<8;i++) {
			TBlack [i].giveValues();
		}
	}

	public static void outWon() {
		System.out.println(n);
		System.out.println("White won "+ countW+ " times");
		System.out.println("Black won "+(n-countW-(n-x))+" times");
		System.out.println("Brown "+countBr);
		System.out.println("Green "+countGr);
		System.out.println("Red "+countR);
		System.out.println("Yellow "+countY);
		System.out.println("Pink "+countPi);
		System.out.println("Purple "+countPu);
		System.out.println("Blue "+countBl);
		System.out.println("Orange "+countO);
	}

}
