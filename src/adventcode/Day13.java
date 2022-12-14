package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day13 {
	
	private int score;
	
	public Day13(LineNumberReader in) throws Exception {
		score = 0;
		int counter = 1;
		
		String line = in.readLine();
		while (line != null) {
			if (!line.equals("")) {
				Day13Packet packet1 = new Day13Packet(line);
				Day13Packet packet2 = new Day13Packet(in.readLine());
				int result = packet1.compareTo(packet2);
				if (result < 0) {
					score += counter;
				}
				counter++;
			}
			line = in.readLine();
		}
	}
	
	public Day13(LineNumberReader in, Day13Packet dividepacket1, Day13Packet dividepacket2) throws Exception {
		score = 0;
		TreeSet<Day13Packet> list = new TreeSet<>();
		list.add(dividepacket1);
		list.add(dividepacket2);
		
		String line = in.readLine();
		while (line != null) {
			if (!line.equals("")) {
				list.add(new Day13Packet(line));
			}
			line = in.readLine();
		}
		score = (list.size() - list.tailSet(dividepacket1).size()+1) * (list.size() - list.tailSet(dividepacket2).size()+1);
	}
	
	public static void main(String[] args) {
		try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]))) {
			Day13 day13 = (new Day13(in1));
			System.out.println("Score part1: "  + day13.score);
			 day13 = new Day13(in2, new Day13Packet("[[2]]"), new Day13Packet("[[6]]"));
			System.out.println("Score part2: "  + day13.score);
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}
