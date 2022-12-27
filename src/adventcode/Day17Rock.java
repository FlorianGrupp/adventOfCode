package adventcode;

public class Day17Rock {
	private int[][] coordinates;
	
	private int[][] type1 = {{0,2},{0,3},{0,4},{0,5}};
	private int[][] type2 = {{0,3},{1,2},{1,3},{1,4},{2,3}};
	private int[][] type3 = {{0,2},{0,3},{0,4},{1,4},{2,4}};
	private int[][] type4 = {{0,2},{1,2},{2,2},{3,2}};
	private int[][] type5 = {{0,2},{0,3},{1,2},{1,3}};
	
	public Day17Rock (int type) {
		if (type == 1) {
			coordinates = type1;
		}
		else if (type == 2) {
			coordinates = type2;
		}
		else if (type == 3) {
			coordinates = type3;
		}
		else if (type == 4) {
			coordinates = type4;
		}
		else if (type == 5) {
			coordinates = type5;
		}
	}
	
	public void setHeight (long height) {
		for (int i=0;i<coordinates.length;i++) {
			coordinates[i][0] += height;
		}
	}
	
	public void moveRight () {
		for (int i=0;i<coordinates.length;i++) {
			if (coordinates[i][1] +1 > 6){
				return;
			}
		}		
		for (int i=0;i<coordinates.length;i++) {
			coordinates[i][1] += 1;
		}
	}
	
	public void moveLeft () {
		for (int i=0;i<coordinates.length;i++) {
			if (coordinates[i][1] -1 < 0){
				return;
			}
		}		
		for (int i=0;i<coordinates.length;i++) {
			coordinates[i][1] -= 1;
		}		
	}
	
	public int getHeight () {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i=0;i<coordinates.length;i++) {
			min = Math.min(min, coordinates[i][0]);
			max = Math.max(min, coordinates[i][0]);
		}
		return max - min +1;
	}
	
	public void moveDown () {
		for (int i=0;i<coordinates.length;i++) {
			coordinates[i][0] -= 1;
		}		
	}
	
	public int[][] getCoordinates () {
		return coordinates;
	}
}
