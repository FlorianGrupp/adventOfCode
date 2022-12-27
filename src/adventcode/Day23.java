package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day23 {
	private TreeMap<String,Day23Elf> elves;

	public Day23 (LineNumberReader in) throws Exception {
		elves = new TreeMap<>();
    	String line = in.readLine();
    	int y=0;
    	while (line != null) {
    		for (int i=0;i<line.length();i++) {
    			if (line.charAt(i) == '#') {
    				elves.put(i+","+y, new Day23Elf(i,y));
    			}
    		}
     		line = in.readLine();
     		y++;
    	}
	}
	
	public int getScore1 (int rounds) {
		for (int i=0;i<rounds;i++) {
			round(i);
		}
		int[] dimensions = getDimensions();
		return ((dimensions[1]-dimensions[0]+1) * (dimensions[3]-dimensions[2]+1)) - elves.values().size();
	}
	
	public int getScore2 () {
		int counter = 0;
		while (round(counter)) {
			counter++;
		}
		return counter+1;
	}
	
	private boolean round (int no) {
		TreeMap<String,ArrayList<Day23Elf>> proposedPositions = new TreeMap<>();
		Iterator<Day23Elf> iter = elves.values().iterator();
		
		//collect proposals
		while (iter.hasNext()) {
			Day23Elf elf = iter.next();
			int[] proposal = elf.proposeMove(no, elves);
			if (proposal != null) {
				ArrayList<Day23Elf> alreadyThere = proposedPositions.get(proposal[0]+"," + proposal[1]);
				if (alreadyThere == null) {
					alreadyThere = new ArrayList<>();
					alreadyThere.add(elf);
					proposedPositions.put(proposal[0]+"," + proposal[1], alreadyThere);
				}
				else {
					alreadyThere.add(elf);
				}
			}
		}
		
		if (proposedPositions.size()==0) {
			return false;
		}
		
		//execute proposals
		Iterator<String> iter2 = proposedPositions.keySet().iterator();
		while (iter2.hasNext()){
			String position = iter2.next();
			if (proposedPositions.get(position).size()<2) {
				Day23Elf elf = proposedPositions.get(position).get(0);
				elves.remove(elf.getX()+","+elf.getY());
				String[] xy = position.split("\\,");
				elf.setX(Integer.parseInt(xy[0]));
				elf.setY(Integer.parseInt(xy[1]));
				elves.put(position, elf);
			}
		}
		return true;
	}
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		int[] dimensions = getDimensions();
		boolean[][] field = new boolean[dimensions[1]-dimensions[0]+1][dimensions[3]-dimensions[2]+1];
		Iterator<Day23Elf> iter = elves.values().iterator();
		
		while (iter.hasNext()) {
			Day23Elf elf = iter.next();
			field[elf.getX()-dimensions[0]][elf.getY()-dimensions[2]] = true;
		}
		
		for (int j=0;j<field[0].length;j++) {
			for (int i=0;i<field.length;i++) {
				if (field[i][j]) {
					sb.append("#");
				}
				else {
					sb.append(".");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	private int[] getDimensions () {
		int[] ret = new int[4];
		ret[0] = Integer.MAX_VALUE;
		ret[2] = Integer.MAX_VALUE;
		ret[1] = Integer.MIN_VALUE;
		ret[3] = Integer.MIN_VALUE;
		
		Iterator<Day23Elf> iter = elves.values().iterator();
		
		while (iter.hasNext()) {
			Day23Elf elf = iter.next();
			ret[0] = Math.min(ret[0], elf.getX());
			ret[1] = Math.max(ret[1], elf.getX());
			ret[2] = Math.min(ret[2], elf.getY());
			ret[3] = Math.max(ret[3], elf.getY());
		}
		return ret;
	}
	
    public static void main(String[] args) {
        try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in3 = new LineNumberReader(new FileReader(args[1]));LineNumberReader in4 = new LineNumberReader(new FileReader(args[1]))) {
            Day23 day23 = (new Day23(in1));
            System.out.println("====> Test Score part1 (110?): " + day23.getScore1(10));
            day23 = (new Day23(in2));
            System.out.println("====> Test Score part2 (20): " + day23.getScore2());
            day23 = (new Day23(in3));
            System.out.println("====> Score part1 (3871?): " + day23.getScore1(10));
            day23 = (new Day23(in4));
            System.out.println("====> Score part2 (925): " + day23.getScore2());
        }
        catch (Exception ex) {
            System.out.println("Upps! Something went wrong!" + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
