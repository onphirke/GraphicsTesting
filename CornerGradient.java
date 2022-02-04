import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class CornerGradient extends DrawTask{
	
	public CornerGradient(Display display) {
		super(display);
		quality = 5;
		speed = 3;
		startCol = new int[] {255, 255, 255};
		endCol = new int[] {50, 200, 200};
		diffCol = new int[] {startCol[0] - endCol[0], startCol[1] - endCol[1], startCol[2] - endCol[2]};
		setUpDcols();
	}

	@Override
	public void draw(Graphics graphics) {
		for(int i = 0; i < frame && i < d.width/quality; i++) {
			for(int j = 0; j < frame && j < d.height/quality; j++) {
				for(int k = 0; k < 3; k++) {
					int temp = dcols[i][j][k];
					if(temp > endCol[k] && temp >= speed) {
						dcols[i][j][k] -= speed * (diffCol[k]/255.0);
					} else {
						dcols[i][j][k] = endCol[k];
					}
				}
				graphics.setColor(new Color(dcols[i][j][0], dcols[i][j][1], dcols[i][j][2]));
				graphics.fillRect(i*quality, j*quality, quality, quality);
			}
		}
	}

}
