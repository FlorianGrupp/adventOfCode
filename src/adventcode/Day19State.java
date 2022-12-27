package adventcode;

public class Day19State implements Comparable<Day19State> {
	
	int oreRobots;
	int clayRobots;
	int obsidianRobots;
	int geodeRobots;
	
	int ore;
	int clay;
	int obsidian;
	int geode;
	
	int minute;
	int maxMinutes;
	
	int[][] blueprint;
	String genom;
	
	public Day19State (int[][] blueprint, int maxMinutes) {
		this.blueprint = blueprint;
		oreRobots = 1;
		minute = 0;
		genom = "";
		this.maxMinutes = maxMinutes;
	}
		
	private void processing () {
		minute++;
		ore += oreRobots;
		clay += clayRobots;
		obsidian += obsidianRobots;
		geode += geodeRobots;
	}
	
	public void waitNextRound () {
		processing();
		genom = genom + "_";
	}
	
	public boolean buildOreRobot () {
		if (blueprint[0][0] > ore) {
			return false;
		}
		processing();
		ore -= blueprint[0][0];
		oreRobots++;
		genom = genom + "O";
		return true;
	}
	
	public boolean buildClayRobot () {
		if (blueprint[0][1] > ore) {
			return false;
		}
		processing();
		ore -= blueprint[0][1];
		clayRobots++;
		genom = genom + "C";
		return true;
	}

	public boolean buildObsidianRobot () {
		if (blueprint[0][2] > ore || blueprint[1][2] > clay) {
			return false;
		}
		processing();
		ore -= blueprint[0][2];
		clay -= blueprint[1][2];
		obsidianRobots++;
		genom = genom + "B";
		return true;
	}

	public boolean buildGeodeRobot () {
		if (blueprint[0][3] > ore || blueprint[2][3] > obsidian) {
			return false;
		}
		processing();
		ore -= blueprint[0][3];
		obsidian -= blueprint[2][3];
		geodeRobots++;
		genom = genom + "G";
		return true;
	}

	private int getValue () {
		int maxGeodes = ((maxMinutes - minute) * geodeRobots) + geode;
		return (maxGeodes * 10000000) + (obsidianRobots * 10000) + (clayRobots * 100) + oreRobots + ((obsidian + ore)*100); 
	}
	
	public Day19State clone () {
		Day19State ret = new Day19State(this.blueprint, maxMinutes);
		ret.oreRobots = this.oreRobots;
		ret.clayRobots = this.clayRobots;
		ret.obsidianRobots = this.obsidianRobots;
		ret.geodeRobots = this.geodeRobots;
		
		ret.ore = this.ore;
		ret.clay = this.clay;
		ret.obsidian = this.obsidian;
		ret.geode = this.geode;
		
		ret.minute = this.minute;		
		ret.genom = this.genom;
		return ret;
	}
	
	public int compareTo (Day19State state) {
		if (state.getValue() - getValue() == 0 ) {
			return 1;
		}
		return state.getValue() - getValue();
	}
}
