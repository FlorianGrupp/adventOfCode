package adventcode;

public class Day20ListItem  {

	private long value;
	
	public Day20ListItem (long value) {
		this.value = value;
	}
	
	public long getValue () {
		return value;
	}
	
	public void setValue (long value) {
		this.value = value;
	}
	
	public String toString() {
		return value+"";
	}
}
