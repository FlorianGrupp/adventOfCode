package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day25 {
	
	private ArrayList<String> lines;

	public Day25 (LineNumberReader in) throws Exception {
		int maxLength = 0;
		lines = new ArrayList<>();
    	String line = in.readLine();
    	
    	while (line != null) {
    	   	lines.add(line);
    	   	maxLength= Math.max(maxLength, line.length());
     		line = in.readLine();
    	}
	}
	
	public String getScore1() {
/*		String s1 = "1=-0-2";
		String s2 = "12111";
		System.out.println(s1 + " + " + s2 + " -> " + add(s1,s2) +  " -> " + convert(add(s1,s2)));*/
		Iterator<String> iter = lines.iterator();
		String sum = "0";
		while (iter.hasNext()) {
			sum = add(sum,iter.next());
		}
		System.out.println(sum + " -> " + convert(sum));
		return sum;
	}
	
	private String add (String number1, String number2) {
		String s1 = format(number1,Math.max(number1.length(), number2.length()));
		String s2 = format(number2,Math.max(number1.length(), number2.length()));
		StringBuilder ret = new StringBuilder();
		int carryover = 0;
		for (int i=s1.length()-1;i>=0;i--) {
			int sum = getDecimal(s1.charAt(i)) + getDecimal(s2.charAt(i)) + carryover;
			carryover = 0;
			if (sum == -5) {
				carryover = -1;
				sum = 0;
			}
			else if (sum == -4) {
				carryover = -1;
				sum = 1;
			}
			else if (sum == -3) {
				carryover = -1;
				sum = 2;
			}
			else if (sum == 3) {
				carryover = 1;
				sum = -2;
			}
			else if (sum == 4) {
				carryover = 1;
				sum = -1;
			}
			else if (sum == 5) {
				carryover = 1;
				sum = 0;
			}
			//System.out.println("   " + s1.charAt(i) + " + " + s2.charAt(i) + " =" +  sum + "," + carryover);
			ret.append(getSNAFU(sum));
		}
		if (carryover != 0) {
			ret.append(getSNAFU(carryover));
		}
		return ret.reverse().toString();
	}
	
	private String format (String s, int noDigits) {
		String ret = s;
		for (int i=0;i<noDigits-s.length();i++) {
			ret = "0" + ret;
		}
		return ret;
	}
	
	private int getDecimal (char c) {
		if (c == '1') {
			return 1;
		}
		else if (c == '2') {
			return 2;
		}
		else if (c == '-') {
			return -1;
		}
		else if (c == '=') {
			return -2;
		}
		return 0;		
	}
	
	private char getSNAFU (int decimal) {
		if (decimal == 1) {
			return '1';
		}
		else if (decimal == 2) {
			return '2';
		}
		else if (decimal == -1) {
			return '-';
		}
		else if (decimal == -2) {
			return '=';
		}
		return '0';				
	}
	
	private long convert (String s) {
		long ret = 0;
		for (int i=0;i<s.length();i++) {
			char c = s.charAt(s.length() -1 - i);
			if (c == '1') {
				ret += Math.pow(5,i);
			}
			else if (c == '2') {
				ret += Math.pow(5,i);
				ret += Math.pow(5,i);
			}
			else if (c == '-') {
				ret -= Math.pow(5,i);
			}
			else if (c == '=') {
				ret -= Math.pow(5,i);
				ret -= Math.pow(5,i);
			}
				
		}
		//System.out.println(s + " -> " + ret);
		return ret;
	}
	
    public static void main(String[] args) {
        try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in3 = new LineNumberReader(new FileReader(args[1]));LineNumberReader in4 = new LineNumberReader(new FileReader(args[1]))) {
            Day25 day25 = (new Day25(in1));
            System.out.println("====> Test Score part1 (4890,2=-1=0?): " + day25.getScore1());
            /*day24 = (new Day24(in2));
            System.out.println("====> Test Score part2 (54): " + day24.getScore2());*/
            day25 = (new Day25(in3));
            System.out.println("====> Score part1 (32005641587247,?): " + day25.getScore1());
            /*day24 = (new Day24(in4));
            System.out.println("====> Score part2 (715): " + day24.getScore2());*/
        }
        catch (Exception ex) {
            System.out.println("Upps! Something went wrong!" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
