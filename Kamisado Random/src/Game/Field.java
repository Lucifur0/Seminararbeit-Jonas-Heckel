package Game;

public class Field {

	public static int [] [] field = {	{0,1,2,3,4,5,6,7},{5,0,3,6,1,4,7,2},{6,3,0,5,2,7,4,1},{3,2,1,0,7,6,5,4},
			{4,5,6,7,0,1,2,3},{1,4,7,2,5,0,3,6},{2,7,4,1,6,3,0,5},{7,6,5,4,3,2,1,0}};
	public static  String [] colors = {"Orange","Blue","Purple","Pink","Yellow","Red","Green","Brown"};
	//									0		1		2		3		4		5		6		7
	public static char [] [] Towerfield = new char [8][8];
	
	public Field () {
		Towerfield[0] = new char[]{1,2,3,4,5,6,7,8};
		Towerfield[7] = new char[]{1,2,3,4,5,6,7,8};
		for (int i = 1; i<7;i++) {
			Towerfield[i] = new char[] {0,0,0,0,0,0,0,0};
		}
		int [] [] field;
	}
	
	
	public static void outMatrix () { //Debug
		for (int i = 0; i<8;i++) {
			for (int j = 0; j<8;j++) {
				System.out.print(colors[field[i][j]]+", ");
			}
			System.out.println(" ");
		}
	}
	
	public static void outField () { //Debug
		for (int i = 0; i<8;i++) {
			for (int j = 0; j<8;j++) {
				System.out.print((int)Towerfield[i][j]+", ");
			}
			System.out.println(" ");
		}
	}
	

	public String colorat(int x, int y) {
		return colors [coloratPos(y, x)];
	}
	
	public int coloratPos (int x, int y) {
		return field [y][x];
	}
	
	
	
}
//static int countBr;
//static int countGr;
//static int countR;
//static int countY;
//static int countPi;
//static int countPu;
//static int countBl;
//static int countO;