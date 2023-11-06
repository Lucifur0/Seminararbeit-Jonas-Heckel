package Game;

public class Turn {
	public Tower current;
	public Tower next;
	public Field Board;
	public Main Teams;
	int Ypos;
	int Xpos; 
	
	public Turn (int Y, int X, Tower t) {
		Ypos = Y;
		Xpos = X;	
		next = null;
		Teams = null;
		current = t;
		Board = Teams.Board;
	}

	public Tower TowernextTurn () {
		if (win() == false) {
			if (current.Team==1) { //White team
				for (int i = 0;i<8;i++) {
					if (Board.coloratPos(current.Posx, current.Posy)==i) {
						next = Teams.TBlack[i];
					}
				}


			}

			else if (current.Team==2) { //Black team
				for (int i = 0;i<8;i++) {
					if (Board.coloratPos(current.Posx, current.Posy)==i) {
						next = Teams.TWhite[7-i];
					}
				}

			}
			System.out.print("The next tower is ");
			next.giveValues();
			Board.outField();
			return next;
		}
		else {
			System.out.print("Team "+current.giveTeamname()+" wins.");
			return null;
		}
	}

	public void TeamMove(int direction, int distance) {
		if (current.Team==1) { //White team
			MoveW ( direction,  distance);

		}
		else if (current.Team==2) { //White team
			MoveB ( direction,  distance);
		}
	}

	public void MoveB (int direction, int distance) { //directiojn = 1->3 //distance = 1->8
		int move = 0;
		if (direction == 3) { //left
			move = (Xpos-distance)*10+Ypos+distance;
		}
		else if (direction == 1) { //right
			move = (Xpos+distance)*10+Ypos+distance;
		}
		else { //Streight
			move = Xpos*10+ Ypos+distance;
		}
		delete(Xpos,Ypos);
		current.changePos(move);	//move a = displacment
		add(current.Posx, current.Posy,(current.Color));

	}

	public void MoveW (int direction, int distance) { //directiojn = 1->3 //distance = 1->8
		int move = 0;
		if (direction == 1) {
			move = (Xpos-distance)*10+Ypos-distance;
		}
		else if (direction == 3) {
			move = (Xpos+distance)*10+Ypos-distance;
		}
		else {
			move =  Xpos*10+Ypos-distance;
		}	
		delete(Xpos,Ypos);
		current.changePos(move);	//move a = displacment 
		add(current.Posx, current.Posy,(7-current.Color));

	}
	
	public boolean win () {
		if (current.Team==1) {
			if (current.Posy== 0) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (current.Posy== 7) {
				return true;
			}
			else {
				return false;
			}
		}

	}

	public void delete (int x, int y) {
		Board.Towerfield [y][x] = 0;
	}

	public void add (int x, int y,int C) {
		Board.Towerfield [y][x] = (char) (C+1);
	}

	public int MaxM () {
		if (current.Team==1) {
			return checkW();
		}
		else {
			return checkB();
		}
	}

	public int checkB () {
		int Max = 0;
		for (int i = 1;i<=(7-Xpos);i++) {//left

			if (Board.Towerfield[Ypos+i][Xpos+i] != 0) {
				System.out.println("There is a tower at "+(Ypos+i)+" "+(Xpos+i)+" colored "+Teams.TWhite[(int) Board.Towerfield[Ypos+i][Xpos+i]-1].givecolorname());
				break;
			}
			else if (Ypos+i == 7 ) {
				Max = (i)*100;
				System.out.println("This Tower can win at "+(Ypos+i-1)+" "+(Xpos+i-1));
				break;
			}
			else {
				Max = (i)*100;
			}
		}
		
		int b = 0;
		for (int i = 1;i<=Xpos;i++) {//right	
			
			
			if (Board.Towerfield[Ypos+i][Xpos-i] != 0 ) {
				System.out.println("There is a tower at "+(Ypos+i)+" "+(Xpos-i)+" colored "+Teams.TWhite[(int) Board.Towerfield[Ypos+i][Xpos-i]-1].givecolorname());
				break;
			}
			else if (Ypos+i == 7 ) {
				b =(i)*10;
				System.out.println("This Tower can win at "+(Ypos+i-1)+" "+(Xpos-i+1));
				break;
			}
			else {
				b =(i)*10;
			}
		}
		Max = Max+b;
		
		b = 0;
		for (int i = 1;i<=(7-Ypos);i++) {//straight	
			
			
			if ( Board.Towerfield[Ypos+i][Xpos] != 0 ) {
				System.out.println("There is a tower at "+(Ypos+i)+" "+(Xpos)+" colored "+Teams.TWhite[(int) Board.Towerfield[Ypos+i][Xpos]-1].givecolorname());
				break;
			}
			else if (Ypos+i == 7 ) {
				b=i;
				System.out.println("This Tower can win at "+(Ypos+i-1)+" "+(Xpos));
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
			
			
			if ( Board.Towerfield[Ypos-i][Xpos-i] != 0 ) {
				System.out.println("There is a tower at "+(Ypos-i)+" "+(Xpos-i)+" colored "+Teams.TBlack[(int) Board.Towerfield[Ypos-i][Xpos-i]-1].givecolorname());
				break;
			}
			else if (Ypos-i == 0 ) {
				Max = (i)*100;
				System.out.println("This Tower can win at "+(Ypos-i+1)+" "+(Xpos-i+1));
				break;
			}
			else {
				Max = (i)*100;
			}
		}

		int b = 0;
		for (int i = 1;i<=(7-Xpos);i++) {//right
			
			
			if ( Board.Towerfield[Ypos-i][Xpos+i] != 0 ) {
				System.out.println("There is a tower at "+(Ypos-i)+" "+(Xpos+i)+" colored "+Teams.TBlack[(int) Board.Towerfield[Ypos-i][Xpos+i]-1].givecolorname());
				break;
			}
			else if (Ypos-i == 0 ) {
				b =(i)*10;
				System.out.println("This Tower can win at "+(Ypos-i+1)+" "+(Xpos+i-1));
				break;
			}
			else {
				b =(i)*10;
			}
		}
		Max = Max+b;


		for (int i = 1;i<=7;i++) {//straight
			
			
			 if ( Board.Towerfield[Ypos-i][Xpos] != 0) {
				System.out.println("There is a tower at "+(Ypos-i)+" "+(Xpos)+" colored "+Teams.TBlack[(int) Board.Towerfield[Ypos-i][Xpos]-1].givecolorname());
				break;
			}
			 else if (Ypos-i == 0 ) {
				b = i;
				System.out.println("This Tower can win at "+(Ypos-i+1)+" "+(Xpos));
				break;
			}
			else {
				b = i;
			}
		}
		Max=Max+b;
		return Max;
	}

	
	
}
