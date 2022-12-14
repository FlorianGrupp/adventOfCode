package adventcode;

import java.util.*;

public class Day14Rocks {
	private ArrayList<int[]> points;
	
	public Day14Rocks (String s) {
		points = new ArrayList<>();
		String[] point = s.split("->");
		for (int i=0;i<point.length;i++) {
			String[] coords = point[i].split(",");
			int[] temp = {Integer.parseInt(coords[0].trim()), Integer.parseInt(coords[1].trim())};
			points.add(temp);
		}
	}
	
	public int[] getMinMaxXY () {
		int[] ret = new int[4];
		ret[0] = Integer.MAX_VALUE;
		ret[1] = Integer.MIN_VALUE;
		ret[2] = Integer.MAX_VALUE;
		ret[3] = Integer.MIN_VALUE;
		
		Iterator<int[]> iter = points.iterator();
		while (iter.hasNext()){
			int[] point = iter.next();
			ret[0] = Math.min(ret[0], point[0]);
			ret[1] = Math.max(ret[1], point[0]);
			ret[2] = Math.min(ret[2], point[1]);
			ret[3] = Math.max(ret[3], point[1]);
		}
		return ret;
	}
	
	public Iterator<int[]> getRockCoordinates() {
		ArrayList<int[]> ret = new ArrayList<>();
		
		Iterator<int[]> iter = points.iterator();
		int[] from = iter.next();
		int[] to = null;
		while (iter.hasNext()) {
			to = iter.next();
			int dx = to[0]-from[0]; 
			int dy = to[1]-from[1]; 
			for (int i=0;i<=Math.max(Math.abs(dx), Math.abs(dy));i++) {
				int[] temp = {from[0]+(i*signum(dx)),from[1]+(i*signum(dy))};
				ret.add(temp);
			}
			from = to;				
		}
		return ret.iterator();
	}
	
	private int signum (int i) {
		if (i==0) {
			return 0;
		}
		if (i<0) {
			return -1;
		}
		return 1;
	}
}
