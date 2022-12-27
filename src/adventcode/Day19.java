package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day19 {
	ArrayList<int[][]> blueprints;
	int maxMinutes = 24;
	
	public Day19 (LineNumberReader in) throws Exception {
		blueprints = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
             String[] split = line.split("\\.");
             int[][] blueprint = new int[3][4];
     		blueprint[0][0] = Integer.parseInt(split[0].substring(split[0].indexOf("costs") + 5, split[0].length()-4).trim());
    		
    		blueprint[0][1] = Integer.parseInt(split[1].substring(split[1].indexOf("costs") + 5, split[1].length()-4).trim());
    		
    		blueprint[0][2] = Integer.parseInt(split[2].substring(split[2].indexOf("costs") + 5, split[2].indexOf("ore")).trim());
    		blueprint[1][2] = Integer.parseInt(split[2].substring(split[2].indexOf("and") + 3, split[2].length()-5).trim());

    		blueprint[0][3] = Integer.parseInt(split[3].substring(split[3].indexOf("costs") + 5, split[3].indexOf("ore")).trim());
    		blueprint[2][3] = Integer.parseInt(split[3].substring(split[3].indexOf("and") + 3, split[3].length()-9).trim());
    		blueprints.add(blueprint);
            line = in.readLine();
        }
    }
	
	public int getScore1 () {
		int score = 0;
		for (int i=0;i<blueprints.size();i++) {
			int maxGeodes = getMaxGeodes(blueprints.get(i));
			System.out.println("blueprint " + (i+1) + ": " + maxGeodes);
			score += (i+1) * maxGeodes;
		}
		return score;
	}
	
	public int getScore2 () {
		int score = 1;
		maxMinutes = 32;
		for (int i=0;i<Math.min(blueprints.size(),3);i++) {
			int maxGeodes = getMaxGeodes(blueprints.get(i));
			System.out.println("blueprint " + (i+1) + ": " + maxGeodes);
			score *= maxGeodes;
		}
		return score;
	}
	
	private int getMaxGeodes (int[][] blueprint) {
		TreeSet<Day19State> current = new TreeSet<>();
		current.add(new Day19State(blueprint,maxMinutes));
		TreeSet<Day19State> next = new TreeSet<>();
		
		for (int i=0;i<maxMinutes;i++) {
			Iterator<Day19State> iter = current.iterator();
			while (iter.hasNext()) {
				Day19State state = iter.next();
				if (i+1 < maxMinutes) {
					Day19State newState = state.clone();
						
					if (newState.buildGeodeRobot()) {
						next.add(newState);							
					}
						
					newState = state.clone();
					if (newState.buildObsidianRobot()) {
						next.add(newState);							
					}

					newState = state.clone();
					if (newState.buildClayRobot()) {
						next.add(newState);							
					}
						
					newState = state.clone();
					if (newState.buildOreRobot()) {
						next.add(newState);							
					}
				}
				Day19State newState = state.clone();
				newState.waitNextRound();
				next.add(newState);					
			}
			current = new TreeSet<>((new ArrayList<Day19State>(next)).subList(0, Math.min(25000, next.size())));
			next = new TreeSet<>();
		}
		Day19State maxGeodes = getMaxGeode(current);
		System.out.print("'" + maxGeodes.genom + "' for ");
		return maxGeodes.geode;
	}
	
	private Day19State getMaxGeode (Collection<Day19State> list) {
		Iterator<Day19State> iter = list.iterator();
		Day19State ret = iter.next();
		while (iter.hasNext()) {
			Day19State state = iter.next();
			if (state.geode > ret.geode) {
				ret = state;
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
	        try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in3 = new LineNumberReader(new FileReader(args[1]));LineNumberReader in4 = new LineNumberReader(new FileReader(args[1]));) {
	            Day19 day19 = (new Day19(in1));
	            System.out.println("====> Test Score part1 (33?): " + day19.getScore1());
	            System.out.println("====> Test Score part2 (3472?): " + day19.getScore2());
	            day19 = (new Day19(in3));
	            System.out.println("====> Score part1 (1264?): " + day19.getScore1());
	            System.out.println("====> Test Score part2 (13475?): " + day19.getScore2());
	        }
	        catch (Exception ex) {
	            System.out.println("Upps! Something went wrong!" + ex.getMessage());
	        }
	    }

}
