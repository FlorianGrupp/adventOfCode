package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day21 {

	private TreeMap<String,String> monkeys;
	private Day21Node root;
	
    public Day21 (LineNumberReader in) throws Exception {
    	monkeys = new TreeMap<>();
    	String line = in.readLine();
    	
    	while (line != null) {
    		String[] split = line.split(":");
    		monkeys.put(split[0],split[1].trim());
    		line = in.readLine();
    	}
    	root = buildTree("root");
   }
    
    private Day21Node buildTree(String name) {
    	String value = monkeys.get(name);
    	Day21Node ret = new Day21Node(name,value);
    	
    	String[] children = null;
    	
    	if (value.contains("+")) {
    		children = value.split("\\+");
    	}
    	else if (value.contains("-")) {
    		children = value.split("\\-");
    	}
    	else if (value.contains("*")) {
    		children = value.split("\\*");
    	}
    	else if (value.contains("/")) {
    		children = value.split("\\/");
    	}

    	if (children != null) {
    		ret.setLeft(buildTree(children[0].trim()));
    		ret.setRight(buildTree(children[1].trim()));
    	}
    	return ret;
    }
    
    public long getScore1() {
    	return root.evaluate();
    }
    
    public long getScore2() {
		Day21Node human = null;
		Day21Node monkey = null;
		
		if (root.getLeft().isHuman()) {
			human = root.getLeft();
			monkey = root.getRight();
		}
		else {
			monkey = root.getLeft();
			human = root.getRight();			
		}
		return human.evaluateReverse(monkey.evaluate());
    }

    public static void main(String[] args) {
        try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[1]))) {
            Day21 day21 = (new Day21(in1));
            System.out.println("====> Test Score part1 (152?): " + day21.getScore1());
            System.out.println("====> Test Score part2 (301?): " + day21.getScore2());
            day21 = (new Day21(in2));
            System.out.println("====> Score part1 (276156919469632?): " + day21.getScore1());
            System.out.println("====> Score part2 (3441198826073): " + day21.getScore2());
        }
        catch (Exception ex) {
            System.out.println("Upps! Something went wrong!" + ex.getMessage());
            ex.printStackTrace();
        }
	}
}
