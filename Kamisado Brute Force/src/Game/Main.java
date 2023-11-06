
package Game;

public class Main {

	static Field Board;
	public static Turn turn;
	static String CTeam;
	static int count;
	static int x;
	static int countW;
	static int countB;
	static int countBr;
	static int countGr;
	static int countR;
	static int countY;
	static int countPi;
	static int countPu;
	static int countBl;
	static int countO;
	public static  String [] colors = {"Orange","Blue","Purple","Pink","Yellow","Red","Green","Brown"};
	public static char [] [] Towerfield = new char [8][8];
	
	public static void main(String[] args) {
		Towerfield[0] = new char[]{9,10,11,12,13,14,15,16};
		Towerfield[7] = new char[]{8,7,6,5,4,3,2,1};
		
		do {
			count=0;
			Board = new Field();
			if(Towerfield.equals(null))
				System.err.print("Something went wrong, Main,main,32");
			Game();	
		} while (x!=0);
		
		outWon();
	}

	public static void Game () {

		for (int i = 0;i<8;i++) {
			first (i);			
		}
	
	}

	public static void first(int i) {
		char Tfirst = Towerfield[7][i];
		turn = new Turn (7,i,Tfirst);
		Turn.TowerField = Towerfield;
		System.out.println(i);
		turn.moves (Towerfield,0);

	}

	public static void colorWon(char C) {
		if (C>8) {
			C=(char)(((int)C)-8);
		}
		if (C==1) {
			countBr++;
		}
		else if(C==2) {
			countGr++;
		}
		else if(C==3) {
			countR++;
		}
		else if(C==4) {
			countY++;
		}
		else if(C==5) {
			countPi++;
		}
		else if(C==6) {
			countPu++;
		}
		else if(C==7) {
			countBl++;
		}
		else if(C==8) {
			countO++;
		}
	}

	public static void outWon() {
		System.out.println("Rounds "+Turn.count);
		System.out.println("Won "+(countW+countB));
		System.out.println("Draw "+(Turn.count-(countW+countB)));
		System.out.println("White won "+ countW+ " times");
		System.out.println("Black won "+(countB)+" times");
		System.out.println("Brown "+countBr);
		System.out.println("Green "+countGr);
		System.out.println("Red "+countR);
		System.out.println("Yellow "+countY);
		System.out.println("Pink "+countPi);
		System.out.println("Purple "+countPu);
		System.out.println("Blue "+countBl);
		System.out.println("Orange "+countO);
	}

	public static String giveColor (char C) {
		if (C>8) {
			C=(char)((int)C-8);
		}
		return colors [C-1];
	}
				
}
