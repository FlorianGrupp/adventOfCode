package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;

public class Day9 {

	protected ArrayList<int[]> moves;
	protected int[][] knots;
	
	//lazy counting of visited coordinates
	protected TreeSet<String> visited;
	
	public Day9 (LineNumberReader in) throws Exception {
		moves = new ArrayList<>();
		
		String line = in.readLine();
		while (line !=null) {
			addMoves(line);
			line = in.readLine();
		}
	}
	
	private void addMoves (String line) {
		String dir = line.substring(0, 1);
		int repeat = Integer.parseInt(line.substring(2));
		for (int i=0;i<repeat;i++) {
			int[] move = new int[2];
			if (dir.equals("R")) {
				move[0] = 1;
			}
			else if (dir.equals("L")) {
				move[0] = -1;
			}
			else if (dir.equals("U")) {
				move[1] = 1;
			}
			else if (dir.equals("D")) {
				move[1] = -1;
			}			
			moves.add(move);
		}
	}
	
	public int simulate (int numberOfKnots) {
		knots = new int[numberOfKnots][2];
		visited = new TreeSet<>();
		visited.add(knots[knots.length-1][0] + "," + knots[knots.length-1][1]);
		Iterator<int[]> iter = moves.iterator();
		while (iter.hasNext()) {
			int[] move = iter.next();
			moveHead(move);
			for (int j=0;j<knots.length-1;j++) {
				moveTail(knots[j],knots[j+1]);
			}
			visited.add(knots[knots.length-1][0] + "," + knots[knots.length-1][1]);
		}
		update();
		return visited.size();
	}
	
	protected void update() {		
	}
	
	private void moveHead (int[] move) {
		knots[0][0] += move[0];
		knots[0][1] += move[1];
	}
	
	private void moveTail(int[] head, int[] tail) {
		int dx = head[0] - tail[0];
		int dy = head[1] - tail[1];
		
		if (Math.abs(dx) == 2 || Math.abs(dy) == 2) {
			if (Math.abs(dx) == 2) {
				dx -= Math.signum(dx); 
			}
			if (Math.abs(dy) == 2) {
				dy -= Math.signum(dy); 
			}
			tail[0] += dx;
			tail[1] += dy;
		}
	}
	
	public static void main(String[] args) {
		try (LineNumberReader in = new LineNumberReader(new FileReader(args[0]))) {
			Day9 day9 = new Day9(in);
			System.out.println("Score part1: " + day9.simulate(2));
			System.out.println("Score part2: " + day9.simulate(10));
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}
