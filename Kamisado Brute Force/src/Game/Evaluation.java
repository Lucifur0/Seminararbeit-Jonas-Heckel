package Game;

/**
 * This class checks the Value of a Position
 * @author Jonas
 *
 */
public class Evaluation {
	public char [] [] TowerF = new char [8] [8];
	int count;
	public char [] [] Best1 = new char [8][8];
	public int Value1;
	public char [] [] Best2 = new char [8][8];
	public int Value2;
	public char [] [] Best3 = new char [8][8];
	public int Value3;
	public char [] [] Best4 = new char [8][8];
	public int Value4;
	public char [] [] Best5 = new char [8][8];
	public int Value5;
	public int valueoverflow;


	public Evaluation (char [] [] TF) {
		TowerF = new char [8] [];
	}
	
	public void EvaW() {
		for (int i = 0;i<8;i++) {
			for (int j = 0;j<8;j++) {
				if (TowerF[j][i]<9 && TowerF[j][i] != 0) {
					checkW(j,i);
				}
				else if (TowerF[j][i]>9 && TowerF[j][i] != 0) {
					checkB(j,i);
				}
			}
		}
	}
	
	public void checkB (int Xpos, int Ypos) {
		int Max = 0;
		for (int i = 1;i<=(7-Xpos);i++) {//left

			if (TowerF[Ypos+i][Xpos+i] != 0) {
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


			if (TowerF[Ypos+i][Xpos-i] != 0 ) {
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


			if (TowerF[Ypos+i][Xpos] != 0 ) {
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
		update(Max);
	}

	public void checkW (int Xpos, int Ypos) {
		int Max = 0;

		for (int i = 1;i<=(Xpos);i++) {//left


			if (TowerF[Ypos-i][Xpos-i] != 0 ) {
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

			if (TowerF[Ypos-i][Xpos+i] != 0 ) {
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


			if (TowerF[Ypos-i][Xpos] != 0) {
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
		updateB(Max);
	}

	public void update (int Max) {
		count+=Max%10; 
		count+=(Max/10)%10;
		count+=(Max/100); //Plus max amount of movement; (16 as maximum)
		
		if (Max >= 1000) { 	//can win
			count+=90;
		}
		if (Max == 0) {		//is blocked 
			count-=100;
		}

		
	}
	
	public void updateB (int Max) {
		count-=Max%10; 
		count-=(Max/10)%10;
		count-=(Max/100);
		
		if (Max >= 1000) { 	//can win
			count-=90;
		}
		if (Max == 0) {		//is blocked 
			count+=110;
		}

		
	}

}
