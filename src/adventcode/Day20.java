package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day20 {
	private ArrayList<Day20ListItem> initialOrder;
	private ArrayList<Day20ListItem> list;
	private Day20ListItem zeroCoord;
	

    public Day20 (LineNumberReader in) throws Exception {
    	initialOrder = new ArrayList<>();
    	zeroCoord = null;
    	
    	String line = in.readLine();
    	
    	while (line != null) {
    		Day20ListItem item = new Day20ListItem(Integer.parseInt(line));
    		if (item.getValue()==0) {
    			zeroCoord = item;
    		}
    		initialOrder.add(item);
    		line = in.readLine();
    	}
   }
    
    public long getScore1 () {
    	list = new ArrayList<>(initialOrder);
    	decrypt();
    	int zeroIndex = list.indexOf(zeroCoord);
    	return list.get((zeroIndex+1000) % list.size()).getValue() + 
    			list.get((zeroIndex+2000) % list.size()).getValue() +
    			list.get((zeroIndex+3000) % list.size()).getValue();
    }
    
    public long getScore2 (long key) {
    	Iterator<Day20ListItem> iter = initialOrder.iterator();
    	
    	while (iter.hasNext()) {
    		Day20ListItem item = iter.next();
    		item.setValue(item.getValue() * key);
    	}
    	list = new ArrayList<>(initialOrder);
    	for (int i=0;i<10;i++) {
        	decrypt();    		
    	}
    	int zeroIndex = list.indexOf(zeroCoord);
    	return list.get((zeroIndex+1000) % list.size()).getValue() + 
    			list.get((zeroIndex+2000) % list.size()).getValue() +
    			list.get((zeroIndex+3000) % list.size()).getValue();
    }
    
    private void decrypt () {
    	Iterator<Day20ListItem> iter = initialOrder.iterator();
    	
    	while (iter.hasNext()) {
    		Day20ListItem item = iter.next();
    		int indexOld = list.indexOf(item);
    		list.remove(item);
    		long indexNew = Math.floorMod(indexOld +item.getValue(), list.size());
    		if (indexNew == 0) {
    			list.add(item);
    		}
    		else {
        		list.add((int)indexNew,item);    			
    		}
    	}
    }
    
    public static void main(String[] args) {
        try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[1]))) {
            Day20 day20 = (new Day20(in1));
            System.out.println("====> Test Score part1 (3?): " + day20.getScore1());
            System.out.println("====> Test Score part2 (1623178306): " + day20.getScore2(811589153L));
            day20 = (new Day20(in2));
            System.out.println("====> Score part1 (2215?): " + day20.getScore1());
            System.out.println("====> Test Score part2 (8927480683?): " + day20.getScore2(811589153L));
        }
        catch (Exception ex) {
            System.out.println("Upps! Something went wrong!" + ex.getMessage());
        }
	}

}
