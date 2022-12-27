package adventcode;

import java.util.*;

public class Day23Elf {
	private int x;
	private int y;
	
	public Day23Elf (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public void setX (int x) {
		this.x = x;		
	}
	
	public void setY (int y) {
		this.y = y;		
	}
	
	private boolean[] checkNeighbors (TreeMap<String,Day23Elf> field) {
		boolean[] ret = new boolean[4];
		//north
		if (field.get((x-1) + "," + (y-1)) != null || 
			field.get((x) + "," + (y-1)) != null || 
			field.get((x+1) + "," + (y-1)) != null) {
			ret[0] = true;
		}
		//south
		if (field.get((x-1) + "," + (y+1)) != null || 
			field.get((x) + "," + (y+1)) != null || 
			field.get((x+1) + "," + (y+1)) != null) {
			ret[1] = true;
		}
		//West
		if (field.get((x-1) + "," + (y-1)) != null || 
			field.get((x-1) + "," + (y)) != null || 
			field.get((x-1) + "," + (y+1)) != null) {
			ret[2] = true;
		}
		//East
		if (field.get((x+1) + "," + (y-1)) != null || 
			field.get((x+1) + "," + (y)) != null || 
			field.get((x+1) + "," + (y+1)) != null) {
			ret[3] = true;
		}
		return ret;
	}
	
	public int[] proposeMove (int round, TreeMap<String,Day23Elf> field) {
		boolean[] hasNeighbor = checkNeighbors(field);
		//no elves around -> no move
		if (!hasNeighbor[0] && !hasNeighbor[1] && !hasNeighbor[2] && !hasNeighbor[3]) {
			return null;
		}
		
		for (int i=round%4;i<(round%4)+4;i++) {
			if (!hasNeighbor[i%4]) {
				int[] ret = new int[2];
				if (i % 4 == 0) {
					ret[0] = x;
					ret[1] = y-1;
				}
				else if (i % 4 == 1) {
					ret[0] = x;
					ret[1] = y+1;
				}
				else if (i % 4 == 2) {
					ret[0] = x-1;
					ret[1] = y;
				}
				else if (i % 4 == 3) {
					ret[0] = x+1;
					ret[1] = y;
				}
				return ret;
			}
		}
		return null;
	}
	
}
