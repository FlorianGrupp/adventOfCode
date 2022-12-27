package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day17 {
	
	private String jetStream;
	private char[][] chamber;
	private long currentHeight;
	private int cursor =0;
	private ArrayList<Day17State> states;
	private long cycleHeight;
	
    public Day17 (LineNumberReader in) throws Exception {
    	jetStream = in.readLine();
    	chamber = new char[2023*5][7];
    	chamber[0][0] = '-';
    	chamber[0][1] = '-';
    	chamber[0][2] = '-';
    	chamber[0][3] = '-';
    	chamber[0][4] = '-';
    	chamber[0][5] = '-';
    	chamber[0][6] = '-';
    	currentHeight = 0;
    	
    	states= new ArrayList<>();
    }
    
    public long getScore1 (long noOfRocks) {
    	
    	int runs = 10000;
    	boolean foundCycle = false;
    	for (long i=1; i<=runs; i++) {
    		int rockType = (int) (((i-1)%5)+1);
    		Day17Rock rock = new Day17Rock(rockType);
    		rock.setHeight(currentHeight+4);
    		fall(rock);
    		
    		Day17State state = new Day17State(this.toString(),cursor,rockType, i,currentHeight );
    		int found = states.indexOf(state);
    		if (found != -1 && !foundCycle) {
    			Day17State oldState = states.get(found);
    			
    			System.out.println(oldState);
    			System.out.println(state);
    			
    			long cycles = noOfRocks/(state.rocksFallen - oldState.rocksFallen);    	    	
    	    	cycleHeight = (state.height - oldState.height) * (cycles-1);
    	    	runs = (int) (noOfRocks - (cycles * (state.rocksFallen - oldState.rocksFallen)) - oldState.rocksFallen);
    	    	runs += i;
    	    	foundCycle = true;  	    	
    		}
    		else {
    			states.add(state);
    		}
    	}
    	return currentHeight + cycleHeight;
    }
    
    private void fall (Day17Rock rock) {
    	while (true) {
    		char jet = jetStream.charAt(cursor);
    		cursor++;
    		if (cursor == jetStream.length()) {
    			cursor = 0;
    		}
    		if (jet == '<' && canMoveLeft(rock)) {
    			rock.moveLeft();
    		}
    		else if (jet == '>' && canMoveRight(rock)) {
    			rock.moveRight();
    		}
    		if (canFall(rock)) {
        		rock.moveDown();    			
    		}
    		else {
    	    	addRock(rock);
    	    	return;
    		}
    	}
    }
      
    private boolean canFall (Day17Rock rock) {
    	int[][] coordinates = rock.getCoordinates();
    	
    	for (int i=0;i<coordinates.length;i++) {
    		if (chamber[coordinates[i][0]-1][coordinates[i][1]] != 0) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean canMoveRight (Day17Rock rock) {
    	try {
        	int[][] coordinates = rock.getCoordinates();
        	
        	for (int i=0;i<coordinates.length;i++) {
        		if (chamber[coordinates[i][0]][coordinates[i][1]+1] != 0) {
        			return false;
        		}
        	}
        	return true;    	    		
    	}
    	catch (Exception ex) {
    		return false;
    	}
    }
    
    private boolean canMoveLeft (Day17Rock rock) {
    	try {
        	int[][] coordinates = rock.getCoordinates();
        	
        	for (int i=0;i<coordinates.length;i++) {
        		if (chamber[coordinates[i][0]][coordinates[i][1]-1] != 0) {
        			return false;
        		}
        	}
        	return true;    	    		
    	}
    	catch (Exception ex) {
    		return false;
    	}
    }
    
    public String toString () {
    	StringBuilder sb = new StringBuilder();
    	boolean drawAir = false;
    	for (int i=chamber.length-1;i>-1;i--) {
    		if (!isAir(i)  || drawAir) {
    			drawAir = true;
    			sb.append("|");
    			for (int j=0;j<chamber[i].length;j++) {
        			if (chamber[i][j] == 0) {
        				sb.append(".");
        			}
        			else {
            			sb.append(chamber[i][j]);        				
        			}
    			}
    			sb.append("|\n");
    		}
    	}
    	return sb.toString();
    }
    
    private void addRock (Day17Rock rock) {
    	int[][] coordinates = rock.getCoordinates();
    	for (int i=0;i<coordinates.length;i++) {
    		chamber[coordinates[i][0]][coordinates[i][1]] = '#';
    	}
    	updateCurrentHeight();
    }
    
    private void updateCurrentHeight () {
    	currentHeight = 0;
       	for (int i=1; i<chamber.length;i++) {
    		if (!isAir(i)) {
    			currentHeight++;
    		}
       	} 	
    }

    public boolean isAir (int row) {
    	return chamber[row][0] == 0 && chamber[row][1] == 0 && chamber[row][2] == 0 && chamber[row][3] == 0 && chamber[row][4] == 0 && chamber[row][5] == 0 && chamber[row][6] == 0;
    }
    
	   public static void main(String[] args) {
	        try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in3 = new LineNumberReader(new FileReader(args[1]));LineNumberReader in4 = new LineNumberReader(new FileReader(args[1]));) {
	            Day17 day17 = (new Day17(in1));
	            System.out.println("====> Test Score part1 (3068?): " + day17.getScore1(2022));
	            day17 = (new Day17(in2));
	            System.out.println("====> Test Score part2 (1514285714288?): " + day17.getScore1(1000000000000L));
	            day17 = (new Day17(in3));
	            System.out.println("====> Score part1 (3211?): " + day17.getScore1(2022));
	            day17 = (new Day17(in4));
	            System.out.println("====> Test Score part2 (1589142857183?): " + day17.getScore1(1000000000000L));
	        }
	        catch (Exception ex) {
	            System.out.println("Upps! Something went wrong!" + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
	
}
