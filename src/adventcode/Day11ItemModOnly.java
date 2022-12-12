package adventcode;

public class Day11ItemModOnly extends Day11Item {
	public static final int MAX_DIVIDE_BY = 23;
	
	private int[] divideBy;
	
	public Day11ItemModOnly (int value) {
		super(value);
		divideBy = new int[MAX_DIVIDE_BY+1];
		for (int i=1; i<=MAX_DIVIDE_BY; i++) {
			divideBy[i] = value % i;
		}
	}
	
	public void add(int x) {
		for (int i=1; i<=MAX_DIVIDE_BY; i++) {
			divideBy[i] = (divideBy[i] + x) % i;
		}		
	}
	
	public void multiply(int x) {
		for (int i=1; i<=MAX_DIVIDE_BY; i++) {
			divideBy[i] = (divideBy[i] * x) % i;
		}				
	}
	
	public void divide(int x) {
	}

	public void addSelf() {
		for (int i=1; i<=MAX_DIVIDE_BY; i++) {
			divideBy[i] = (divideBy[i] + divideBy[i]) % i;
		}						
	}

	public void multiplySelf() {
		for (int i=1; i<=MAX_DIVIDE_BY; i++) {
			divideBy[i] = (divideBy[i] * divideBy[i]) % i;
		}						
	}
	
	public int getDivideBy (int i) {
		return divideBy[i];
	}
}
