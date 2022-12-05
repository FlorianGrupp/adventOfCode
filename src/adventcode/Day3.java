package adventcode;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Day3 {

	private char getDuplicateChar (String[] s) {
		for (int i=0;i<s[0].length();i++) {
			boolean found = true;
			for (int j=1;j<s.length;j++) {
				found &= s[j].indexOf(s[0].charAt(i)) !=-1;
			}
			if (found) {
				return s[0].charAt(i);
			}
		}
		return ' ';
	}

	private int getValueOfChar (char c) {
		if(c < 91) {
			return c-38;
		}
		return c-96;
	}
	
	
	public int calculateScore(LineNumberReader in) throws IOException {
		String line = in.readLine();
		int score = 0;
		while (line != null) {
			String[] s = new String[2];
			s[0] = line.substring(0, (line.length()/2));
			s[1] = line.substring(line.length()/2, line.length());
			score += getValueOfChar(getDuplicateChar(s));
			line = in.readLine();
		}
		return score;
	}
	
	public int calculateScore2(LineNumberReader in) throws IOException {
		String line1 = in.readLine();
		String line2 = in.readLine();
		String line3 = in.readLine();
		int score = 0;
		while (line1 != null) {
			String[] s = new String[3];
			s[0] = line1;
			s[1] = line2;
			s[2] = line3;
			score += getValueOfChar(getDuplicateChar(s));
			line1 = in.readLine();
			line2 = in.readLine();
			line3 = in.readLine();
		}
		return score;
	}

	public static void main(String[] args) throws Exception {
		try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]))) {
			System.out.println("Score part1: " + (new Day3()).calculateScore(in1));
			System.out.println("Score part2: " + (new Day3()).calculateScore2(in2));
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}
