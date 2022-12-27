package adventcode;

public class Day15Range implements Comparable<Day15Range> {
	private int from;
	private int to;
	
	public Day15Range (int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public int getLength () {
		return to - from + 1;
	}
	
	public int getFrom () {
		return from;
	}
	
	public int getTo () {
		return to;
	}
	
	public boolean merge (Day15Range other) {
		if (this.getTo()+1 < other.getFrom() || other.getTo()+1 < this.getFrom()) {
			return false;
		}
		from = Math.min(this.getFrom(), other.getFrom());
		to = Math.max(this.getTo(), other.getTo());
		return true; 
	}

	public int compareTo (Day15Range other) {
		return Integer.compare(from, other.getFrom());
	}
	
	public String toString () {
		return "[" + from + ";" + to + "]";
	}
}
