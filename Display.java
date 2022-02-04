import java.awt.Graphics;
import java.io.SequenceInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Display extends JFrame {

	public int width = 768, height = 768;
	public ArrayList<DrawTask> tasks = new ArrayList<DrawTask>(1);
	public DisplayManager dm;
	
	public Display() {
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
		
		tasks.add(new ColorGradient(this));
		dm = new DisplayManager(this);
		dm.start();
	}
	
	public void paint(Graphics g) {
		//g.clearRect(0, 0, width, height);
		for(int i = 0; i < tasks.size(); i++) {
			tasks.get(i).draw(g);
		}
	}
	
	public static void main(String[] args) {
		new Display();
	}
	
}

class DisplayManager extends Thread {
	
	Display display;
	public int FPS = 50;
	
	public DisplayManager(Display display) {
		this.display = display;
	}
	
	public void run() {
		while(true) {
			try {
				display.tasks.get(0).frame++;
				display.repaint();
				Thread.sleep(1000/FPS);
				//System.out.println(display.tasks.get(0).frame);
			} 
			catch(InterruptedException e) {
				break;
			}
		}
	}
	
}
