package adventcode;

public class Day11Item {

	protected int value;
	
	public Day11Item(int value) {
		this.value = value;
	}
	
	public void add(int x) {
		 value += x;		
	}
	
	public void multiply(int x) {
		value *= x;				
	}
	
	public void divide(int x) {
		value /= x;
	}

	public void addSelf() {
		value += value;
	}

	public void multiplySelf() {
		value *= value;
	}
	
	public int getDivideBy (int i) {
		return value % i;
	}
}
