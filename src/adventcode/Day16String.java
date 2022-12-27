package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class Day16String {

	private TreeMap<String,Integer> flowRates;
	private TreeMap<String,Integer> distances;
	private ArrayList<String> valves;
	private int maxMinutes = 30;
	
	private int maxPressure1;
	private String maxPath1;
	
	private int maxPressure2;
	private String maxPath2a;
	private String maxPath2b;
	
	public Day16String (LineNumberReader in) throws Exception {
		Day16CaveParser temp = new Day16CaveParser(in);
		flowRates = temp.getFlowRates();
		distances = temp.getDistances();
		valves = temp.getRelevantCaves();
	}
	
	public int getLength (String path) {
		int ret = 0;
		for (int i=0;i<=path.length()/2-2;i++) {
			String from = path.substring(i*2,(i*2)+2);
			String to = path.substring((i*2)+2,(i*2)+4);
			if (from.compareTo(to) < 0) {
				ret += distances.get(from+to);				
			}
			else {
				ret += distances.get(to+from);								
			}
		}
		//ret++;
		return ret;
	}

	public int getValue (String path) {
		int elapsed = 0;
		int ret = 0;
		for (int i=0;i<=path.length()/2-2;i++) {
			String from = path.substring(i*2,(i*2)+2);
			String to = path.substring((i*2)+2,(i*2)+4);
			int dist = 0;
			if (from.compareTo(to) < 0) {
				dist = distances.get(from+to);				
			}
			else {
				dist = distances.get(to+from);								
			}
			ret += ((maxMinutes - elapsed)*flowRates.get(from));
			elapsed += dist;
			elapsed++;
		}
		if (elapsed < maxMinutes) {
			ret += (maxMinutes - elapsed)*flowRates.get(path.substring(path.length()-2));
		}
		return ret;	
	}
	
	public int getMaxPressure1 () {
		maxMinutes = 30;
		calculateMaxPressure1("AA");
		return maxPressure1;
	}
	
	public int getMaxPressure2 () {
		maxMinutes = 26;
		calculateMaxPressure2("AA");
		return maxPressure2;
	}

	private void calculateMaxPressure1 (String path) {
		Iterator<String> iter = valves.iterator();
		while (iter.hasNext()) {
			String nextValve = iter.next();
			if (path.indexOf(nextValve) == -1) {
				path = path + nextValve;
				if (getLength(path) + ((path.length()/2) -1) < maxMinutes) {
					int pressure = getValue(path);
					if (pressure > maxPressure1) {
						maxPressure1 = pressure;
						maxPath1 = new String(path);
						System.out.println(maxPath1 + ": " + maxPressure1);
					}
					calculateMaxPressure1(path);
				}
				path = path.substring(0,path.length()-2);
			}
		}
	}
	

	private void calculateMaxPressure2 (String path) {
		for (int i=0;i<valves.size();i++) {
			String nextValve = valves.get(i);
			if (path.indexOf(nextValve) == -1) {
				String newPath = new String(path + nextValve);
				String[] newPathes = split(newPath);
				if (getLength((newPathes[0]) + ((newPathes[0].length()/2) -1)) <= maxMinutes && getLength(newPathes[1] + ((newPathes[0].length()/2) -1)) <= maxMinutes) {
					int pressure = getValue(newPathes[0]) + getValue(newPathes[1]);
					if (pressure > maxPressure2) {
						maxPressure2 = pressure;
						maxPath2a = newPathes[0];
						maxPath2b = newPathes[1];
						System.out.println(maxPath2a + "," + maxPath2b + ": " + maxPressure2);
					}
					calculateMaxPressure2(newPath);
				}
			}
		}
	}
	
	private String[] split (String s) {
		String[] ret = new String[2];
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		sb2.append("AA");
		for (int i=0;i<(s.length()+1)/2;i++) {
			if (i % 2== 0) {
				sb1.append(s.substring(i*2, (i*2) +2));
			}
			else {
				sb2.append(s.substring(i*2, (i*2) +2));				
			}
		}
		ret[0] = sb1.toString();
		ret[1] = sb2.toString();
		return ret;
	}

	public static void main(String[] args) {
		try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[1]))) {
			Day16String day16 = (new Day16String(in1));
			System.out.println("Test Score part1 (?1651): " + day16.getMaxPressure1());
			System.out.println("Test Score part2 (?1707): " + day16.getMaxPressure2());
			day16 = (new Day16String(in2));
			long time1 = System.currentTimeMillis();
			System.out.println("Score part1 (?2059): " + day16.getMaxPressure1());
			long time2 = System.currentTimeMillis();
			System.out.println("  time elapsed " + (time2-time1));
			time1 = System.currentTimeMillis();
			System.out.println("Score part2 (?2790): " + day16.getMaxPressure2());
			time2 = System.currentTimeMillis();
			System.out.println("  time elapsed " + (time2-time1));
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}
