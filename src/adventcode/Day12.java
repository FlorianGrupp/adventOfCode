package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day12 {
	
	protected int[][] heightMap;
	protected int[][] minSteps;
	protected int destX;
	protected int destY;
	
	public Day12(LineNumberReader in, boolean scenic) throws Exception {

		ArrayList<String> lines = new ArrayList<>();
		String line = in.readLine();
		while (line !=null) {
			lines.add(line);
			line = in.readLine();
		}
		heightMap =new int[lines.get(0).length()][lines.size()];
		minSteps =new int[lines.get(0).length()][lines.size()];
		
		for (int j=0;j<lines.size();j++) {
			for (int i=0;i<lines.get(0).length();i++) {
				char c = lines.get(j).charAt(i);
				heightMap[i][j] = c;					
				minSteps[i][j] = Integer.MAX_VALUE;
				if (c == 'S') {
					heightMap[i][j] = 'a';
					minSteps[i][j] = 0;
				}
				else if (c == 'E') {
					heightMap[i][j] = 'z';
					destX = i;
					destY = j;
				}
				else if (c == 'a' && scenic) {
					minSteps[i][j] = 0;
				}
			}			
		}
	}
	
	public int minimumReach () {

		for (int k=0;k<heightMap.length*heightMap[0].length;k++) {
			for (int i=0;i<heightMap.length;i++) {
				for (int j=0;j<heightMap[0].length;j++) {
					int min = getMinSurroundingSteps(i,j);
					if (min<minSteps[i][j]) {
						minSteps[i][j] = min;						
					}
				}
			}		
		}
		return minSteps[destX][destY];
	}
	
	private int getMinSurroundingSteps (int x, int y) {
		int up = Integer.MAX_VALUE;
		int down = Integer.MAX_VALUE;
		int left = Integer.MAX_VALUE;
		int right = Integer.MAX_VALUE;
		
		if (isMovePossible(x,y,0,-1)) {
			up = minSteps[x][y-1];
		}
		if (isMovePossible(x,y,0,1)) {
			down = minSteps[x][y+1];
		}
		if (isMovePossible(x,y,-1,0)) {
			left = minSteps[x-1][y];
		}
		if (isMovePossible(x,y,1,0)) {
			right = minSteps[x+1][y];
		}
		int min = Math.min(Math.min(up, down), Math.min(right, left));
		if (min != Integer.MAX_VALUE) {
			min += 1;
		}
		return min;
	}
	
	protected boolean isMovePossible(int x, int y, int dx, int dy) {
		try {
			return (heightMap[x][y] - heightMap[x+dx][y+dy]) < 2;			
		}
		catch (Exception ex) {
			return false;
		}
	}
		
	public static void main(String[] args) {
		try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]))) {
			Day12 day12 = (new Day12(in1,false));
			System.out.println("Score part1: "  + day12.minimumReach());
			day12 = (new Day12(in2,true));	
			System.out.println("Score part2: "  + day12.minimumReach());
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}