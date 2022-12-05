package adventcode;

import java.io.*;
import java.util.*;

public class Day1 {

	public AbstractCollection<Integer> getSortedList(LineNumberReader in) throws IOException {
		TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() 
		{
			//descending order, do not eliminate duplicates
			public int compare(Integer o1, Integer o2) { 
				if (o1 > o2) {
					return -1;
				} 
				else { 
					return 1;
				}
			}
		} );
		int current = 0;
		String line = in.readLine();
		while (line != null) {
			if (line.equals("")) {
				set.add(current);
				current = 0;
			}
			else {
				current += Integer.parseInt(line);
			}
			line = in.readLine();
		}
		return set;
	}

	public static void main(String[] args) {
		try (LineNumberReader in = new LineNumberReader(new FileReader(args[0]))) {
			Integer[] result = new Day1().getSortedList(in).toArray(new Integer[0]);
			System.out.println("Record part one: " + result[0]);
			System.out.println("Record part two: " + (result[0]+result[1]+result[2]));
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}

}

