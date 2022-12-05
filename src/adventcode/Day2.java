package adventcode;

import java.io.*;

public class Day2 {
	
	//[0][0] = score for 'A X', [0][1] = score for 'A Y', ...
	public static final int[][] SCORE_1 = {{4,8,3},{1,5,9},{7,2,6}};
	public static final int[][] SCORE_2 = {{3,4,8},{1,5,9},{2,6,7}};

	private int decodeX (String s) {
		if (s.startsWith("A")) {
			return 0;
		}
		else if (s.startsWith("B")) {
			return 1;
		}
		else if (s.startsWith("C")) {
			return 2;
		}
		return -1;
	}
	
	private int decodeY (String s) {
		if (s.endsWith("X")) {
			return 0;
		}
		else if (s.endsWith("Y")) {
			return 1;
		}
		else if (s.endsWith("Z")) {
			return 2;
		}
		return -1;
	}

	public int calculateScore(LineNumberReader in, int[][] matrix) throws IOException {
		String line = in.readLine();
		int score = 0;
		while (line != null) {
			score += matrix[decodeX(line)][decodeY(line)];
			line = in.readLine();
		}
		return score;
	}
	
	public static void main(String[] args) throws Exception {
		try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]))) {
			System.out.println("Score part one: " + (new Day2()).calculateScore(in1, Day2.SCORE_1));
			System.out.println("Score part two: " + (new Day2()).calculateScore(in2, Day2.SCORE_2));
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}
