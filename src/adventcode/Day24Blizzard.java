package adventcode;

public class Day24Blizzard {
	private int x;
	private int y;
	private char dir;
	
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	
	public Day24Blizzard (int x, int y, int[] boundaries, char dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		
		this.minX = boundaries[0];
		this.maxX = boundaries[1];
		this.minY = boundaries[2];
		this.maxY = boundaries[3];		
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public char getDir () {
		return dir;
	}
	
	public void move () {
		if (dir == '>') {
			 x++;
		}
		else if (dir == '<') {
			 x--;
		}
		else if (dir == 'v') {
			 y++;
		}
		else if (dir == '^') {
			 y--;
		}
		
		if (x> maxX) {
			x = minX;
		}
		if (x< minX) {
			x = maxX;
		}
		if (y> maxY) {
			y = minY;
		}
		if (y< minY) {
			y = maxY;
		}
	}
}
