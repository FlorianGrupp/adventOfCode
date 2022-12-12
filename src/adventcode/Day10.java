package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;

public class Day10 {
	private int signalStrength;
	private int cycles;
	private int x;
	private String screen;
	
	public Day10 (LineNumberReader in) throws Exception {		
		StringBuilder sb = new StringBuilder();
		x = 1;
		cycles = 0;
		signalStrength = 0;
		
		String line = in.readLine();
		while (line !=null) {
			int repeat = 1;
			int add = 0;
			if (line.startsWith("addx")) {
				repeat = 2;
				add = Integer.parseInt(line.substring(5));
			}
			for (int i=0;i<repeat;i++) {
				cycles++;
				int position = (cycles % 40)-1;
				if ((x-1) <= position && position <= (x+1)) {
					sb.append("#");
				}
				else {
					sb.append(".");
				}
				if (cycles == 20 || cycles == 60 || cycles == 100 || cycles == 140 || cycles == 180 || cycles == 220) {
					signalStrength += cycles * x;
				}
				if (cycles == 40 || cycles == 80 || cycles == 120 || cycles == 160 || cycles == 200 || cycles == 240) {
					sb.append("\n");
				}
			}
			x +=add;
			line = in.readLine();
		}
		screen = sb.toString();
	}
	

	public static void main(String[] args) {
		try (LineNumberReader in = new LineNumberReader(new FileReader(args[0]))) {
			Day10 day10 = new Day10(in);
			System.out.println("Score part1: " + day10.signalStrength);
			System.out.println("Score part2: \n" +day10.screen);
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}
