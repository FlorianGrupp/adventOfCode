package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;

public class Day8 {
	
	protected int[][] trees;
	
	protected int[][] score1Matrix;
	protected int score1;
	
	protected int[][] score2Matrix;
	protected int score2;

	public Day8(LineNumberReader in) throws Exception {
		
		String line = in.readLine();
		trees = new int[line.length()][line.length()];
		score1Matrix = new int[line.length()][line.length()];
		score2Matrix = new int[line.length()][line.length()];
		
		int counter = 0;
		while (line !=null) {
			for (int i=0;i<line.length();i++) {
				trees[i][counter] = Integer.parseInt(line.substring(i,i+1));
			}
			counter++;
			line = in.readLine();
		}
		calculateScore1();
		calculateScore2();
	}
	
	public int getScore1() {
		return score1;
	}
	
	public int getScore2() {
		return score2;
	}
	
	private void calculateScore1 () {		
		int[][] maxFromRight = createMaxMatrix(1,0);
		int[][] maxFromDown	= createMaxMatrix(0,1);
		int[][] maxFromLeft = createMaxMatrix(-1,0);
		int[][] maxFromUp	= createMaxMatrix(0,-1);
		
		score1 = 0;
		for (int i=0;i<trees[0].length;i++) {
			for (int j=0;j<trees.length;j++) {
				if (i == 0 || j == 0 || i == trees[0].length-1 || j == trees.length-1 || trees[i][j] > maxFromUp[i][j-1] || trees[i][j] > maxFromDown[i][j+1] || trees[i][j] > maxFromRight[i+1][j] || trees[i][j] > maxFromLeft[i-1][j]) {
					score1++;
					score1Matrix[i][j] = 1;
				}		
			}
		}
	}
	
	private int[][] createMaxMatrix (int dx, int dy) {
		int[][] ret = new int[trees[0].length][trees.length];
		
		if (dx > 0 || dy > 0) {
			for (int i=trees[0].length-1;i>=0;i--) {
				for (int j=trees.length-1;j>=0;j--) {
					try {
						ret[i][j] = Math.max(trees[i][j],ret[i+dx][j+dy]);
					}
					catch (Exception ex) {
						ret[i][j] = trees[i][j];
					}
				}
			}						
		}
		else {
			for (int i=0;i<trees[0].length;i++) {
				for (int j=0;j<trees.length;j++) {
					try {
						ret[i][j] = Math.max(trees[i][j],ret[i+dx][j+dy]);
					}
					catch (Exception ex) {
						ret[i][j] = trees[i][j];
					}
				}
			}			
		}
		return ret;
	}

	private int scoreUp (int x, int y) {
		if  (y == 0) {
			return 0;
		}
		int score = 1;
		
		for (int i=y-1;i>0;i--) {
			if (trees[x][i]<trees[x][y]) {
				score++;
			}
			else {
				return score;
			}
		}
		return score;
	}
	
	private int scoreDown (int x, int y) {
		if  (y == trees[0].length-1) {
			return 0;
		}
		int score = 1;
		
		for (int i=y+1;i<trees[0].length-1;i++) {
			if (trees[x][i]<trees[x][y]) {
				score++;
			}
			else {
				return score;
			}
		}
		return score;
	}
	
	private int scoreLeft (int x, int y) {
		if  (x == 0) {
			return 0;
		}
		int score = 1;
		
		for (int i=x-1;i>0;i--) {
			if (trees[i][y]<trees[x][y]) {
				score++;
			}
			else {
				return score;
			}
		}
		return score;
	}

	private int scoreRight (int x, int y) {
		if  (x == trees[0].length-1) {
			return 0;
		}
		int score = 1;
		
		for (int i=x+1;i<trees[0].length-1;i++) {
			if (trees[i][y]<trees[x][y]) {
				score++;
			}
			else {
				return score;
			}
		}
		return score;
	}
	
	//very quick and dirty
	private void calculateScore2 () {
		score2 = 0;
		for (int i=0;i<trees.length;i++) {
			for (int j=0;j<trees[0].length;j++) {
				int scenicScore = scoreUp(i,j) * scoreDown(i,j) * scoreLeft(i,j) * scoreRight(i,j);
				score2Matrix[i][j] = scenicScore;
				if (scenicScore > score2) {
					score2 = scenicScore;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try (LineNumberReader in = new LineNumberReader(new FileReader(args[0]))) {
			Day8 day8 = new Day8(in);
			System.out.println("Score part1: " + (day8.getScore1()));
			System.out.println("Score part2: " + (day8.getScore2()));
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}
