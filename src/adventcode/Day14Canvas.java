package adventcode;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileReader;
import java.io.LineNumberReader;

import javax.swing.*;

public class Day14Canvas extends JPanel {
	
	private Day14 day14;
	
	public Day14Canvas (Day14 day14) {
		this.day14 = day14;
	}
	
	public void paintComponent (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 1400, 1000);
		
		char[][] cave = day14.getCave();
		for (int j=0;j<cave[0].length;j++) {
			for (int i=0;i<cave.length;i++) {
				if (cave[i][j] == 0) {
					g2d.setColor(Color.BLACK);					
				}
				if (cave[i][j] == 'o') {
					g2d.setColor(Color.GREEN);					
				}
				if (cave[i][j] == '+') {
					g2d.setColor(Color.yellow);					
				}
				if (cave[i][j] == '#') {
					g2d.setColor(Color.RED);					
				}
				g2d.fillRect((i*4) + 30, (j*4) + 30, 4, 4);			
			}
		}
		
	}
	
	public static void main(String[] args) {
		try (LineNumberReader in = new LineNumberReader(new FileReader(args[0]))) {
			Day14 day14 = (new Day14(in,true));
			day14.fallingSand();
			JFrame frame = new JFrame("Day14");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout(5,5));
			frame.add(new Day14Canvas(day14),BorderLayout.CENTER);
			frame.setSize(1400,1000);
			frame.setVisible(true);
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
