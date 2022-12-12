package adventcode;

import java.util.*;

public class Day11Monkey {
	
	private ArrayList<Day11Item> items;
	private String operator;
	private String operand;
	private int divisor;
	private int noTrue;
	private int noFalse;
	private int inspectCounter;
	
	public Day11Monkey (String[] lines, boolean modOnly) {
		items = new ArrayList<>();
		String[] linesItems = lines[0].substring(lines[0].indexOf(":")+1).split(",");
		for (int i=0;i<linesItems.length;i++) {
			int value = Integer.parseInt(linesItems[i].trim());
			if (modOnly) {
				items.add(new Day11ItemModOnly(value));				
			}
			else {
				items.add(new Day11Item(value));				
			}
		}
		operator = lines[1].substring(lines[1].indexOf("old")+4,lines[1].indexOf("old")+5);
		operand = lines[1].substring(lines[1].indexOf(operator)+1).trim();
		divisor = Integer.parseInt(lines[2].substring(lines[2].indexOf("by")+3).trim());
		noTrue = Integer.parseInt(lines[3].substring(lines[3].indexOf("monkey") + 6).trim());
		noFalse = Integer.parseInt(lines[4].substring(lines[4].indexOf("monkey") + 6).trim());
	}
	
	public void inspect() {
		if (items.size()>0) {
			Day11Item item = items.get(0);
			if (operand.equals("old") && operator.equals("+")) {
				item.addSelf();
			}
			else if (operand.equals("old") && operator.equals("*")) {
				item.multiplySelf();
			}
			else if (operator.equals("+")) {
				item.add(Integer.parseInt(operand));
			}
			else if (operator.equals("*")) {
				item.multiply(Integer.parseInt(operand));
			}
			item.divide(3);
			inspectCounter++;
		}		
	}
	
	public int thrownToMonkey () {
		Day11Item item = items.get(0);
		if (item.getDivideBy(divisor) == 0) {
			return noTrue;
		}
		return noFalse;
	}
	
	public Day11Item throwItem() {
		Day11Item item = items.get(0);
		items.remove(0);
		return item;		
	}
	
	public void catchItem (Day11Item item) {
		items.add(item);
	}
	
	public int getInspected() {
		return inspectCounter;
	}
	
	public boolean hasItems() {
		return items.size() > 0;
	}
}
