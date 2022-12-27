package adventcode;

public class Day15Sensor {

	private int sensorX;
	private int sensorY;
	private int beaconX;
	private int beaconY;
	private int distance;
	
	public Day15Sensor (String line) {
		this.sensorX = Integer.parseInt(line.substring(line.indexOf("x")+2,line.indexOf("y")-2).trim());
		this.sensorY = Integer.parseInt(line.substring(line.indexOf("y")+2,line.indexOf(":")).trim());
		this.beaconX = Integer.parseInt(line.substring(line.lastIndexOf("x")+2,line.lastIndexOf("y")-2).trim());
		this.beaconY = Integer.parseInt(line.substring(line.lastIndexOf("y")+2).trim());	
		distance = Math.abs(sensorX-beaconX) + Math.abs(sensorY-beaconY);
	}
	
	public int getDistance () {
		return distance;
	}
	
	public int getBeaconX () {
		return beaconX;
	}
	
	public int getBeaconY () {
		return beaconY;
	}

	public Day15Range coverFromY (int y) {
		if (Math.abs(sensorY - y) > distance) {
			return null;
		}
		else {
			return new Day15Range(sensorX - (distance - Math.abs(sensorY - y)) , sensorX + (distance - Math.abs(sensorY - y)));
		}
	}
}
