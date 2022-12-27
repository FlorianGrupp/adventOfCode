package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day15 {
	private ArrayList<Day15Sensor> signals;

	public Day15 (LineNumberReader in) throws Exception {
		signals = new ArrayList<>();
		String line = in.readLine();
		while (line != null) {
			signals.add(new Day15Sensor(line));			
			line = in.readLine();
		}
	}
		
	private ArrayList<Day15Range> getRowCoverage (int row) {
		ArrayList<Day15Range> ret = new ArrayList<>();
		Iterator<Day15Sensor> iter = signals.iterator();
		while (iter.hasNext()) {
			Day15Range temp = iter.next().coverFromY(row);
			if (temp != null) {
				ret.add(temp);
			}
		}
		return merge(ret);
	}
	
	private ArrayList<Day15Range> merge (ArrayList<Day15Range> list) {
		Collections.sort(list);
		
		ArrayList<Day15Range> ret = new ArrayList<>();
		ret.add(list.get(0));
		list.remove(0);
		
		Iterator<Day15Range> iter = list.iterator();
		while (iter.hasNext()) {
			Day15Range next = iter.next();
			if (!ret.get(ret.size()-1).merge(next)) {
				ret.add(next);
			}
		}
		return ret;
	}	
	
	public int getScore1 (int row) {
		int score = 0;
		Iterator<Day15Range> iter = getRowCoverage(row).iterator();
		while (iter.hasNext()) {
			score += iter.next().getLength();
		}
		score -= countBeacon(row);
		return score;
	}
	
	private int countBeacon (int row) {
		TreeMap<Integer,Day15Sensor> ret = new TreeMap<>();
		Iterator<Day15Sensor> iter = signals.iterator();
		while (iter.hasNext()) {
			Day15Sensor temp = iter.next();
			if (temp.getBeaconY() == row) {
				ret.put(temp.getBeaconY(),temp);
			}
		}
		return ret.size();
	}
	
	public long getScore2 (int from, int to) {
		for (int i = from;i<=to;i++) {
			ArrayList<Day15Range> temp = getRowCoverage(i);
			if (temp.size()>1) {
				long mult = to;
				return ((temp.get(0).getTo()+1) * mult) + i;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]))) {
			Day15 day15 = (new Day15(in1));
			System.out.println("Score part1 (5142231?): " + day15.getScore1(Integer.parseInt(args[1])) );
			System.out.println("Score part2 (10884459367718?): " + day15.getScore2(Integer.parseInt(args[2]),Integer.parseInt(args[3])));
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
