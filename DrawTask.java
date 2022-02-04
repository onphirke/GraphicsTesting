import java.awt.Graphics;
import java.util.Arrays;

public abstract class DrawTask {
	
	public int frame;
	public abstract void draw(Graphics g);
	public void nextFrame() {
		frame++;
	}
	
	Display d;
	int[][][] dcols;
	int quality = 5;
	int speed = 5;
	int[] startCol = {255, 255, 255};
	int[] endCol = {0, 0, 0};
	int[] diffCol = {startCol[0] - endCol[0], startCol[1] - endCol[1], startCol[2] - endCol[2]};
	
	public DrawTask(Display display) {
		this.d = display;
		
		setUpDcols();
		
		System.out.println(Arrays.toString(startCol) + "    ");
	}
	
	public void setUpDcols() {
		dcols = new int[d.width/quality][d.height/quality][3];
		for(int i = 0; i < dcols.length; i++) {
			for(int j = 0; j < dcols[i].length; j++) {
				dcols[i][j][0] = startCol[0];
				dcols[i][j][1] = startCol[1];
				dcols[i][j][2] = startCol[2];
			}
		}
	}
	
}
