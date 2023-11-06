package Game;

public class Tower {

	public int Team;
	public int Color;
	public static  String [] colors = {"Orange","Blue","Purple","Pink","Yellow","Red","Green","Brown"};
	public int Posy;
	public int Posx;
	
	public Tower (int T, int C, int py, int px) {
		Team = T;
		Color = C;
		Posy = py;
		Posx = px;

		
	}
	
	public void giveValues () { //Debug
		if ( Team == 1) {
			System.out.println("White "+colors[Color]);
		}
		else if ( Team == 2) {
			System.out.println("Black "+colors[Color]);
		}
		System.out.println("the Tower stants at "+ Posy+ " "+Posx);
	}

	public void changePos (int move) {
		int newy = move%10;
		int newx = move/10;
		Posy = newy;
		Posx = newx;
	}
	
	public int giveposy () {
		return Posy;
	}
	
	public int giveposx () {
		return Posx;
	}
	
	public int givecolor() {
		return Color;
	}
	
	public String givecolorname() {
		return colors[Color];
	}
	
	public String giveTeamname() {
		if (Team == 1) {
			return "White";
		}
		else {
			return "Black";
		}
	}
	
}
