package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;

public class Day6 {
	
	public int calculateScore (String s, int markerLength) {
		for (int i=0;i<s.length()-markerLength;i++) {
			if (isDistinct(s.substring(i,i+markerLength))) {
				return i+markerLength;
			}
		}
		return -1;
	}
	
	private boolean isDistinct (String s) {
		for (int i=0;i<s.length();i++) {
			if (! (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		try (LineNumberReader in = new LineNumberReader(new FileReader(args[0]))) {
			System.out.println("Score part1: " + (new Day6().calculateScore(in.readLine(),Integer.parseInt(args[1]))));
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}
