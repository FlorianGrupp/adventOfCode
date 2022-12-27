package adventcode;

public class Day17State {
	
	String chamber;
	int jetIndex;
	int rocksType;
	long rocksFallen;
	long height;
	
	public Day17State (String chamber, int jetIndex, int rocksType, long rocksFallen, long height ) {
		this.chamber = chamber;
		this.jetIndex = jetIndex;
		this.rocksType = rocksType;
		this.rocksFallen = rocksFallen;
		this.height = height;
	}

	public boolean equals (Object obj) {
		Day17State otherState = (Day17State) obj;
		if (jetIndex != otherState.jetIndex) {
			return false;
		}
		if (rocksType != otherState.rocksType) {
			return false;
		}
		if (!chamber.substring(0,Math.min(200, chamber.length())).equals(otherState.chamber.substring(0,Math.min(200, otherState.chamber.length()))) ) {
			return false;
		}
		return true;
	}
	
	public String toString () {
		return "{rocksFallen=" + rocksFallen + ", height=" + height + "}";
	}
}
