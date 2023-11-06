package Game;

public class Turn {

	int Ypos;
	int Xpos; 
	char Current;
	static long count;
	char next;
	public static char[] [] TowerField = new char [8] [];
	private int MaxRounds = 8;
	public static int round;

	public Turn (int Y, int X, char C ) {
		Ypos = Y;
		Xpos = X;	
		Current = C;
	}
	
	public void TeamMove(int direction, int distance) {
		if (Current<9) { //White team
			MoveW ( direction,  distance);

		}
		else { //Black team
			MoveB ( direction,  distance);
		}
	}

	//sets Next
	public void MoveB (int direction, int distance) { //directiojn = 1->3 //distance = 1->8
		delete(Xpos,Ypos);
		if (direction == 3) { //left
			add(Xpos-distance,Ypos+distance);
//			System.out.println(Main.Board.coloratPos(Xpos-distance, Ypos+distance)+1);
			next= (char) (Main.Board.coloratPos(Xpos-distance, Ypos+distance)+1);
		}
		else if (direction == 1) { //right
			add(Xpos+distance,Ypos+distance);
//			System.out.println(Main.Board.coloratPos(Xpos+distance, Ypos+distance)+1);
			next= (char) (Main.Board.coloratPos(Xpos+distance, Ypos+distance)+1);
		}
		else { //Streight
			add(Xpos,Ypos+distance);
//			System.out.println(Main.Board.coloratPos(Xpos, Ypos+distance)+1);
			next= (char) (Main.Board.coloratPos(Xpos, Ypos+distance)+1);
		}

	}

	//sets Next
	public void MoveW (int direction, int distance) { //directiojn = 1->3 //distance = 1->8
		delete(Xpos,Ypos);
		if (direction == 1) {
			add(Xpos-distance,Ypos-distance);
//			System.out.println( Main.Board.coloratPos(Xpos-distance, Ypos-distance)+9);
			next=(char)( Main.Board.coloratPos(Xpos-distance, Ypos-distance)+9);
		}
		else if (direction == 3) {
			add(Xpos+distance,Ypos-distance);
//			System.out.println(Main.Board.coloratPos(Xpos+distance, Ypos-distance)+9);
			next= (char) (Main.Board.coloratPos(Xpos+distance, Ypos-distance)+9);
		}
		else {
			add(Xpos,Ypos-distance);
//			System.out.println(Main.Board.coloratPos(Xpos, Ypos-distance)+9);
			next= (char) (Main.Board.coloratPos(Xpos, Ypos-distance)+9);
		}	

	}

	public void delete (int x, int y) {
		TowerField [y][x] = 0;
	}

	public void add (int x, int y) {
		TowerField [y][x] = Current;
	}

	public int MaxM () {
		if (Current<9) {
			return checkW();
		}
		else {
			return checkB();
		}
	}

	public int checkB () {
		int Max = 0;
		for (int i = 1;i<=(7-Xpos);i++) {//left

			if (TowerField[Ypos+i][Xpos+i] != 0) {
				break;
			}
			else if (Ypos+i >= 7 ) {
				Max = 1000;
				break;
			}
			else {
				Max = (i)*100;
			}
		}

		int b = 0;
		for (int i = 1;i<=Xpos;i++) {//right	


			if (TowerField[Ypos+i][Xpos-i] != 0 ) {
				break;
			}
			else if (Ypos+i >= 7 ) {
				Max = 1000;
				break;
			}
			else {
				b =(i)*10;
			}
		}
		Max = Max+b;

		b = 0;
		for (int i = 1;i<=(7-Ypos);i++) {//straight	


			if (TowerField[Ypos+i][Xpos] != 0 ) {
				break;
			}
			else if (Ypos+i >= 7 ) {
				Max = 1000;
				break;
			}
			else {
				b = i;
			}
		}
		Max=Max+b;
		return Max;
	}

	public int checkW () {
		int Max = 0;

		for (int i = 1;i<=(Xpos);i++) {//left


			if (TowerField[Ypos-i][Xpos-i] != 0 ) {
				break;
			}
			else if (Ypos-i <= 0 ) {
				Max = 1000;
				break;
			}
			else {
				Max = (i)*100;
			}
		}

		int b = 0;
		for (int i = 1;i<(8-Xpos);i++) {//right

			if (TowerField[Ypos-i][Xpos+i] != 0 ) {
				break;
			}
			else if (Ypos-i <= 0 ) {
				Max = 1000;
				break;
			}
			else {
				b =(i)*10;
			}
		}
		Max = Max+b;

		b=0;
		for (int i = 1;i<=7;i++) {//straight


			if (TowerField[Ypos-i][Xpos] != 0) {
				break;
			}
			else if (Ypos-i <= 0 ) {
				Max = 1000;
				break;
			}
			else {
				b = i;
			}
		}
		Max=Max+b;
		return Max;
	}

	public int nextpos() {
		for (int i = 0;i<8;i++) {
			for (int j = 0;j<8;j++) {
				if (TowerField[i][j]==next) {
					return (i*10+j);
				}
			}
		}
		return 0;
	}
	
	public void UpdateArray(char [][] TF) {
		
		TowerField = new char [8][];
		
		for (int i = 0;i<8;i++) {
			TowerField[i] = TF[i].clone();					
		}
//		for (char[] row : TowerField) {
//			for (char c : row) {
//				System.out.print((int)c + " ");
//			}
//			System.out.println();
//		}

	}
	
	public void moves (char [] [] TF, int b) {

		int Max = MaxM();
		
		if (b>=MaxRounds) { // end of Turns Reached			
		}
		
		else if (Max >= 1000) { // Tower can win
			if (Current<9) {
				Main.countW++;
			}
			else {
				Main.countB++;
			}
			Main.colorWon(Current);
			
		}
		else if (Max == 0) {
			TeamMove(2, 0);
			int PosNext = nextpos();
			Turn nextTurn = new Turn (PosNext/10,PosNext%10,next);
			nextTurn.moves(TowerField, b+1);

		}
		else {
			if (Max%10!=0) { //straight
				
				for (int i = 1;i<=Max%10;i++) {
					
					UpdateArray(TF);	
					
//					System.out.println("Turn number: "+b);
//					outField ();
					TeamMove(2, i);
					count ++;
//					System.out.println();
//					outField();
					int PosNext = nextpos();
					Turn nextTurn = new Turn (PosNext/10,PosNext%10,next);
					nextTurn.moves(TowerField,b+1);
				}//end straight

				if (Max/100 != 0) { // left
					for (int i = 1;i<=Max/100;i++) {

						UpdateArray(TF);	
						
//						System.out.println("Turn number: "+b);
//						outField ();
						TeamMove(1, i);
						count ++;
//						System.out.println();
//						outField();
						int PosNext = nextpos();
						Turn nextTurn = new Turn (PosNext/10,PosNext%10,next);
						nextTurn.moves(TowerField,b+1);
					}
				}//end left

				if (((Max/10)%10)!=0) { // right
					for (int i = 1;i<=((Max/10)%10);i++) {

						UpdateArray(TF);	
						
//						System.out.println("Turn number: "+b);
//						outField ();
						TeamMove(3, i);
						count ++;
//						System.out.println();
//						outField();
						int PosNext = nextpos();
						Turn nextTurn = new Turn (PosNext/10,PosNext%10,next);						nextTurn.moves(TowerField,b+1);
					}
				}//end right

			}//end els if's

		}//end Else
//		System.out.println("Count = "+count);
		
	}//end Moves

	public static void outField () { //Debug
		for (int i = 0; i<8;i++) {
			for (int j = 0; j<8;j++) {
				System.out.print((int)TowerField[i][j]+", ");
			}
			System.out.println(" ");
		}
	}

	private String giveTeam (char C) {
		if (C<9) {
			return "White";

		}
		else {
			return "Black";
		}
	}	
}
