package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Iterator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//very quick and dirty solution
public class Day9Visualization extends Day9 {

	private Day9Canvas canvas;
	private JTextField numberOfKnots;
	
	public Day9Visualization (LineNumberReader in) throws Exception {
		super(in);
		
		JFrame frame = new JFrame("Day8");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(5,5));
		frame.setSize(1000,1000);
		frame.setVisible(true);
		
		JPanel top = new JPanel();
		numberOfKnots = new JTextField("10");
		JButton simulate = new JButton("Simulate");
		
		top.add(numberOfKnots);
		top.add(simulate);
		frame.add(top,BorderLayout.NORTH);
		
		canvas = new Day9Canvas(this);
		frame.add(canvas,BorderLayout.CENTER);
		
		simulate.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("simulating...");
				System.out.println("result: " + simulate(Integer.parseInt(numberOfKnots.getText())));
			}
			public void mouseEntered(MouseEvent e) {
				
			}
			public void mouseExited(MouseEvent e) {
				
			}
			public void mousePressed(MouseEvent e) {
				
			}
			public void mouseReleased(MouseEvent e) {
				
			}
		});
	}
	
	public int[] getDimension () {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		
		int x = 0;
		int y = 0;
		
		Iterator<int[]> iter = moves.iterator();
		while (iter.hasNext()) {
			int[] move = iter.next();
			x += move[0];
			y += move[1];
			
			if (x < minX) {
				minX = x;
			}
			if (x > maxX) {
				maxX = x;
			}
			if (y < minY) {
				minY = y;
			}
			if (y > maxY) {
				maxY = y;
			}
		}
		int[] ret = new int[4];
		ret[0] = minX;
		ret[1] = minY;
		ret[2] = maxX;
		ret[3] = maxY;
		return ret;
	}
	
	public int[][] getKnots () {
		return knots;
	}
	
	public Iterator<String> getVisited() {
		return visited.iterator();
	}

	protected void update() {
		canvas.repaint();		
	}

	public static void main(String[] args) {
		try (LineNumberReader in = new LineNumberReader(new FileReader(args[0]))) {
			new Day9Visualization(in);
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}

}
