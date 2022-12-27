package adventcode;

import java.util.*;

public class Day16Cave implements Comparable<Day16Cave>{
	
	private String name;
	private int flowRate;
	private ArrayList<Day16Cave> tunnels;
	
	public Day16Cave (String name, int flowRate, ArrayList<Day16Cave> tunnels) {
		this.name = name;
		this.flowRate = flowRate;
		this.tunnels = tunnels;
	}

	public Day16Cave (String name) {
		this(name, 0, new ArrayList<>());
	}
	
	public String getName () {
		return name;
	}
	
	public int getFlowRate () {
		return flowRate;
	}
	
	public ArrayList<Day16Cave> getTunnels () {
		return tunnels;
	}
	
	public void setFlowRate (int flowRate) {
		this.flowRate = flowRate;
	}
	
	public void setTunnels (ArrayList<Day16Cave> tunnels) {
		this.tunnels = tunnels;
	}
	
	public boolean equals (Object o) {
		if (o != null && o instanceof Day16Cave) {
			return name.equals(((Day16Cave) o).getName());
		}
		return false;
	}
	
	public int compareTo (Day16Cave other) {
		return name.compareTo(other.getName());
	}
	
	public String toString() {
		return name;
	}
	
}
