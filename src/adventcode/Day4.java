package adventcode;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Day4 {
	
	private int[] decode (String s) {
		String[] sa = s.split(",");
		String[] s1 = sa[0].split("-");
		String[] s2 = sa[1].split("-");
		int[] ret = new int[4];
		ret[0] = Integer.parseInt(s1[0]);
		ret[1] = Integer.parseInt(s1[1]);
		ret[2] = Integer.parseInt(s2[0]);
		ret[3] = Integer.parseInt(s2[1]);
		return ret;
	}

	public int[] calculateScore(LineNumberReader in) throws IOException {
		String line = in.readLine();
		int score1 = 0;
		int score2 = 0;
		while (line != null) {
			int[] areas = decode(line);
			if ((areas[0] <= areas[2] && areas[1] >= areas[3]) || (areas[0] >= areas[2] && areas[1] <= areas[3])) {
				score1++;
			}
			if ( (areas[1] >= areas[2] && areas[0]<=areas[2]) || (areas[0] >= areas[2] && areas[0] <= areas[3] )) {
				score2++;
			}
			line = in.readLine();			
		}
		int[] ret = new int[2];
		ret[0] = score1;
		ret[1] = score2;
		return ret;
	}

	public static void main(String[] args) throws Exception {
		try (LineNumberReader in = new LineNumberReader(new FileReader(args[0]))) {
			int[] res = (new Day4()).calculateScore(in);
			System.out.println("Score part1: " + res[0]);
			System.out.println("Score part2: " + res[1]);
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}	
}