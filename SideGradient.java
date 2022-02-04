import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class SideGradient extends DrawTask{
	
	public SideGradient(Display display) {
		super(display);
		quality = 4;
		speed = 10;
		startCol = new int[] {255, 255, 255};
		endCol = new int[] {50, 200, 200};
		diffCol = new int[] {startCol[0] - endCol[0], startCol[1] - endCol[1], startCol[2] - endCol[2]};
		setUpDcols();
	}

	@Override
	public void draw(Graphics graphics) {
		for(int i = 0; i < d.width/quality; i++) {
			for(int j = 0; j < d.height/quality; j++) {
				for(int k = 0; k < 3; k++) {
					int temp = dcols[i][j][k];
					boolean test = i < frame;
					if(temp > endCol[k] && temp >= speed && test) {
						dcols[i][j][k] -= speed * (diffCol[k]/255.0);
					} else if (test){
						dcols[i][j][k] = endCol[k];
					}
				}
				graphics.setColor(new Color(dcols[i][j][0], dcols[i][j][1], dcols[i][j][2]));
				graphics.fillRect(i*quality, j*quality, quality, quality);
			}
		}
	}

}
