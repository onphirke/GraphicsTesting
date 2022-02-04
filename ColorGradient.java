import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class ColorGradient extends DrawTask{
	
	public ColorGradient(Display display) {
		super(display);
		quality = 1;
		speed = 1;
		startCol = new int[] {0, 0, 0};
		endCol = new int[] {0, 0, 0};
		diffCol = new int[] {startCol[0] - endCol[0], startCol[1] - endCol[1], startCol[2] - endCol[2]};
		setUpDcols();
	}
	
	@Override
	public void setUpDcols() {
		dcols = new int[d.width/quality][d.height/quality][3];
		int shift = d.width/3 + 50;
		for(int i = 0; i < dcols.length; i++) {
			for(int j = 0; j < dcols[i].length; j++) {
				dcols[i][j][0] = (int)(255 - ((i - 50) * (j - 50)) / (d.width/speed));
				dcols[i][j][1] = (int)(255 - ((i - shift - 200) * (j - shift - 100)) / (d.width/speed));
				dcols[i][j][2] = (int)(255 - ((i - shift *2 - 50) * (j - shift * 2) - 50) / (d.width/speed));
				//dcols[i][j][0] = (int)(255 - (i+j) * 2);
				//dcols[i][j][1] = 127 - (i+j);
				for(int k = 0; k < 3; k++) {
					int temp = dcols[i][j][k];
					if(temp > 255){
						dcols[i][j][k] = 255;
					}
					else if(temp < 0) {
						dcols[i][j][k] = 0;
					}
				}
			}
		}
	}
	
	@Override
	public void draw(Graphics graphics) {
		System.out.println(frame);
		for(int i = 0; i < d.width/quality; i++) {
			for(int j = 0; j < d.height/quality; j++) {
				graphics.setColor(new Color(dcols[i][j][0], dcols[i][j][1], dcols[i][j][2]));
				graphics.fillRect(i*quality, j*quality, quality, quality);
			}
		}
		d.dm.interrupt();
	}

}
