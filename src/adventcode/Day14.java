package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day14 {
	
	private ArrayList<Day14Rocks> rocks;
	private int[] minMaxXY;
	private char[][] cave;
	private int translateX;
	private boolean hasNirvana;
	
	public Day14 (LineNumberReader in, boolean hasNirvana) throws Exception {
		this.hasNirvana = hasNirvana;
		rocks = new ArrayList<>();
		minMaxXY = new int[4];
		minMaxXY[0] = Integer.MAX_VALUE;
		minMaxXY[1] = Integer.MIN_VALUE;
		minMaxXY[2] = Integer.MAX_VALUE;
		minMaxXY[3] = Integer.MIN_VALUE;

		String line = in.readLine();
		while (line != null) {
			Day14Rocks trace = new Day14Rocks(line);
			rocks.add(trace);
			int[] traceMinMaxXY = trace.getMinMaxXY();
			minMaxXY[0] = Math.min(minMaxXY[0], traceMinMaxXY[0]);
			minMaxXY[1] = Math.max(minMaxXY[1], traceMinMaxXY[1]);
			minMaxXY[2] = Math.min(minMaxXY[2], traceMinMaxXY[2]);
			minMaxXY[3] = Math.max(minMaxXY[3], traceMinMaxXY[3]);
			line = in.readLine();
		}
		initCave(hasNirvana);
	}
	

	private void initCave (boolean hasNirvana) {
		if (hasNirvana) {
			cave = new char[minMaxXY[1]-minMaxXY[0]+3][minMaxXY[3]+1];
			translateX = ((-1) * minMaxXY[0])+1;
		}
		else {
			cave = new char[(2*minMaxXY[3])+5][minMaxXY[3]+3];			
			for (int i=0;i<cave.length;i++) {
				cave[i][cave[0].length-1] = '#';
				translateX =  minMaxXY[3] - 500 +2;
			}
		}
		cave[translateX(500)][translateY(0)] = '+';
		Iterator<Day14Rocks> iter = rocks.iterator();
		while (iter.hasNext()){
			Iterator<int[]> singleRocks = iter.next().getRockCoordinates();
			while (singleRocks.hasNext()) {
				int[] temp = singleRocks.next();
				cave[translateX(temp[0])][translateY(temp[1])] = '#';
			}
		}
	}

	public char[][] getCave () {
		return cave;
	}
	
	public int fallingSand () {
		int counter = 0;
		while (fallSand (500, 0)) {
			counter++;
		}
		if (!hasNirvana) {
			counter++;
		}
		return counter;		
	}
	
	private boolean fallSand (int x, int y) {
		if (y>=cave[0].length-1) {
			return false;
		}		
		if (cave[translateX(x)][translateY(y)+1] == 0) {
			return fallSand (x, y+1);
		}
		else if (cave[translateX(x-1)][translateY(y)+1] == 0) {
			return fallSand (x-1, y+1);
		}
		else if (cave[translateX(x+1)][translateY(y)+1] == 0) {
			return fallSand (x+1, y+1);
		}
		else {
			if (x == 500 && y ==0) {
				return false;
			}
			cave[translateX(x)][translateY(y)] = 'o';
			return true;			
		}
	}
	
	private int translateX (int x) {
		return x + translateX;
	}
	
	private int translateY (int y) {
		return y;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int j=0; j<cave[0].length; j++) {
			for (int i=0; i<cave.length;i++) {
				if (cave[i][j] == 0) {
					sb.append(".");				
				}
				else {
					sb.append(cave[i][j]);					
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]))) {
			Day14 day14 = (new Day14(in1,true));
			System.out.println("Score part1: "  + day14.fallingSand());
			day14 = new Day14(in2,false);
			System.out.println("Score part2: "  + day14.fallingSand());
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
