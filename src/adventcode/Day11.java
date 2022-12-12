package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day11 {
	
	private ArrayList<Day11Monkey> monkeys;

	public Day11 (LineNumberReader in, boolean modOnly) throws Exception {		
		monkeys = new ArrayList<>();
		
		String line = in.readLine();
		while (line !=null) {
			String[] args = new String[5];
			for (int i=0; i<5;i++) {
				args[i] = in.readLine();
			}
			monkeys.add(new Day11Monkey(args,modOnly));
			line = in.readLine();
			line = in.readLine();
		}
	}

	public void play (int rounds) {
		for (int i=1;i<=rounds;i++) {
			playRound();
		}
	}
	
	private void playRound() {
		for (int i=0;i<monkeys.size();i++) {
			Day11Monkey monkey = monkeys.get(i);
			while (monkey.hasItems()) {
				monkey.inspect();
				monkeys.get(monkey.thrownToMonkey()).catchItem(monkey.throwItem());
			}
		}
	}
	
	public long getMonkeyBusiness () {
		TreeSet<Integer> list = new TreeSet<>();
		for (int i=0;i<monkeys.size();i++) {
			list.add(monkeys.get(i).getInspected());
		}
		return ((long)list.pollLast()) * ((long)list.pollLast());
	}
	
	public static void main(String[] args) {
		try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]))) {
			Day11 day11 = new Day11(in1,false);
			day11.play(Integer.parseInt(args[1]));
			System.out.println("Score part1: "  + day11.getMonkeyBusiness());
			day11 = new Day11(in2,true);
			day11.play(Integer.parseInt(args[2]));
			System.out.println("Score part2: " + day11.getMonkeyBusiness());
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}
