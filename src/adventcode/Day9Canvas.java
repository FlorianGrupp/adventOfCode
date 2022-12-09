package adventcode;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

//very quick and dirty solution
public class Day9Canvas extends JPanel {
	private Day9Visualization day9;
	
	public Day9Canvas (Day9Visualization day9) {
		this.day9 = day9;
	}
	
	public void paintComponent (Graphics g) {
		int[][] knots = day9.getKnots();
		if (knots !=null ) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.clearRect(0, 0, 800, 800);
			
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, 800, 800);
			
			g2d.setColor(Color.GRAY);
			Iterator<String> iter = day9.getVisited();
			while(iter.hasNext()) {
				String visited = iter.next();
				
				int[] helper = new int[2];
				helper[0] = Integer.parseInt(visited.substring(0, visited.indexOf(",")));
				helper[1] = Integer.parseInt(visited.substring(visited.indexOf(",")+1));
				
				int[] xy = translate (helper);
				g2d.fillOval(xy[0], xy[1], 3, 3);
				
			}
			
			g2d.setColor(Color.BLUE);
			for (int i=0;i<knots.length;i++) {
				int[] xy = translate (knots[i]);
				g2d.fillOval(xy[0], xy[1], 3, 3);
			}			
			for (int i=0;i<knots.length-1;i++) {
				int[] xy1 = translate (knots[i]);
				int[] xy2 = translate (knots[i+1]);
				g2d.drawLine(xy1[0]+1, xy1[1]+1, xy2[0]+1, xy2[1]+1);
			}		
			
			g2d.setColor(Color.RED);
			int[] helper = new int[2];
			int[] xy = translate (helper);
			g2d.fillOval(xy[0], xy[1], 3, 3);
		}
	}
	
	private int[] translate (int[] knot) {
		int[] maxDimension = day9.getDimension();
		int[] ret = new int[2];
		
		ret[0] = knot[0] - maxDimension[0];			
		ret[1] = (maxDimension[3] - maxDimension[1]) - (knot[1] - maxDimension[1]);			

		int scale = 800 / Math.max(maxDimension[2] - maxDimension[0], maxDimension[3] - maxDimension[1]);
		ret[0] = ret[0] * scale;  
		ret[1] = ret[1] * scale;  
		return ret;
		
	}
}
