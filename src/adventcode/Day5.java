package adventcode;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;

public class Day5 {

	private ArrayList<String>[] crateStacks;
	private ArrayList<Integer[]> crateMoves;
	
	public Day5 (LineNumberReader in) throws IOException {
		String line = in.readLine();
		crateStacks = new ArrayList[(line.length()+1)/4];
		for (int i=0;i<crateStacks.length;i++) {
			crateStacks[i] = new ArrayList<>();
		}
		crateMoves = new ArrayList<>();
		
		while (line !=null) {
			if (line.startsWith("move")) {
				parseCrateMoves(line);				
			}
			else if (line.indexOf("[")!=-1) {
				parseCrateStacks(line);				
			}
			line = in.readLine();
		}
	}
	
	private void parseCrateStacks (String line) {
		for (int i=0;i<crateStacks.length;i++) {
			if (!line.substring((i*4)+1, (i*4)+2).equals(" ")) {
				crateStacks[i].add(line.substring((i*4)+1, (i*4)+2));					
			}
		}		
	}
	
	private void parseCrateMoves(String line) {
		Integer[] temp = new Integer[3];
		temp[0] = Integer.parseInt(line.substring(5, line.indexOf("from")-1));
		temp[1]  = Integer.parseInt(line.substring(line.indexOf("from")+5,line.indexOf("to")-1))-1;
		temp[2]  = Integer.parseInt(line.substring(line.indexOf("to")+3))-1;
		crateMoves.add(temp);
	}
	
	private void move (int moves, int from, int to, String mode) {
		ArrayList<String> helper = new ArrayList<String>();
		for (int i=0;i<moves;i++) {
			if (mode.equals("1")) {
				helper.add(0,crateStacks[from].get(0));
			}
			else {
				helper.add(crateStacks[from].get(0));				
			}
			crateStacks[from].remove(0);
		}
		crateStacks[to].addAll(0,helper);
	}
	
	public String getTopCrates() {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<crateStacks.length;i++) {
			sb.append(crateStacks[i].get(0));
		}
		return sb.toString();
	}
	
	public void moveCrates(String mode) {
		Iterator<Integer[]> iter = crateMoves.iterator();
		while (iter.hasNext()) {
			Integer[] moves = iter.next();
			move(moves[0],moves[1],moves[2],mode);		
		}
	}
	
	public static void main(String[] args) throws Exception {
		try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]))) {
			Day5 day5 = new Day5(in1);
			day5.moveCrates(args[1]);
			System.out.println(day5.getTopCrates());
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}		
}