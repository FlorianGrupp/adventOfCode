package adventcode;

import java.util.ArrayList;

public class Day13Packet implements Comparable<Day13Packet> {

	private ArrayList<Day13Packet> childPackets;
	private int value;
	private boolean isInteger;
	
	public Day13Packet(String s) {
		childPackets = new ArrayList<>();
		if (!s.startsWith("[")) {
			isInteger = true;
			value = Integer.parseInt(s);
		}
		else {
			createChildPackets(s.substring(1,s.length()-1));
		}
	}
	
	private void createChildPackets (String s) {
		int countBrackets = 0;
		StringBuilder sb = new StringBuilder();
		
		for (int i=0;i<s.length();i++) {
			if (countBrackets == 0 && s.charAt(i) == ',') {
				childPackets.add(new Day13Packet(sb.toString()));
				sb = new StringBuilder();
			}
			else {
				if (s.charAt(i) == '[') {
					countBrackets++;
				}
				else if (s.charAt(i) == ']') {
					countBrackets--;
				}
				sb.append(s.charAt(i));
			}
		}
		if (sb.length()!= 0) {
			childPackets.add(new Day13Packet(sb.toString()));			
		}
	}
	
	public int compareTo (Day13Packet obj) {
		if (isInteger && obj.isInteger) {
			return value - obj.value;
		}
		else if (isInteger && !obj.isInteger) {
			return new Day13Packet("["+ value + "]").compareTo(obj);
		}
		else if (!isInteger && obj.isInteger){
			return this.compareTo(new Day13Packet("["+ obj.value + "]"));
		}
		else {
			for (int i=0; i< Math.min(childPackets.size(), obj.childPackets.size());i++) {
				int compare = childPackets.get(i).compareTo(obj.childPackets.get(i));
				if (compare != 0 ) {
					return compare;
				}
			}
			return childPackets.size() - obj.childPackets.size();
		}
	}	
}
