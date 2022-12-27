package adventcode;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Day22 {

	private ArrayList<String> moves;
	private char[][] tiles;
	private int maxX;
	private int maxY;
	
	private int[][] directions = { {1,0},{0,1},{-1,0},{0,-1}};
	private int dirIndex;
	private int curPosX;
	private int curPosY;
	
    public Day22 (LineNumberReader in) throws Exception {

    	ArrayList<String> tileLines = new ArrayList<>();
    	String line = in.readLine();
    	maxX = 0;
    	maxY = 0;
    	
    	while (line != null) {
    		if (line.equals("")) {
    			parseMoves(in.readLine());
    		}
    		else {
    			maxX = Math.max(maxX,line.length());
    			maxY++;
    	   		tileLines.add(line);			
    		}
     		line = in.readLine();
    	}
    	parseTiles(tileLines);
   }
    
    private void parseTiles(ArrayList<String> tileLines) {
    	tiles = new char[maxX][maxY];

    	for (int j=0;j<tileLines.size();j++) {
    		String line = tileLines.get(j);
    		for (int i=0;i<line.length();i++) {
    			if (line.charAt(i) == ' ') {
    				tiles[i][j] = 0;   				
    			}
    			else {
    				tiles[i][j] = line.charAt(i);
    			}
    		}    				
    	}
    }
    
    private void parseMoves (String line) {
    	moves = new ArrayList<>();
    	
    	for (int i=0;i<line.length();i++) {
    		if (line.charAt(i) == 'R' || line.charAt(i) == 'L') {
    			moves.add(line.substring(i,i+1));
    		}
    		else {
    			int index1 = line.indexOf('R', i);
    			int index2 = line.indexOf('L', i);
    			//end of line
    			if (index1 == -1 && index2 == -1) {
        			index1= line.length();   				
        			index2= line.length();   				
    			}
    			//only L left
    			else if (index1 == -1) {
    				index1 = index2;
    			}
    			//only R left
    			else if (index2 == -1) {
    				index2 = index1;
    			}
    			moves.add(line.substring(i,Math.min(index1, index2)));
    			i+=Math.min(index1, index2)-i-1;
    		}
    	}
    }

    private void print () {
    	for (int j=0;j<maxY;j++) {
    		for (int i=0;i<maxX;i++) {
    			if (tiles[i][j] == 0){
        			System.out.print(" ");    				
    			}
    			else {
        			System.out.print(tiles[i][j]);    				    				
    			}
    		}
    		System.out.println();
    	}
    }
    
    private int getRightMostTile (int y) {
    	for (int i=maxX-1;i>=0;i--) {
    		if (tiles[i][y] != 0 ) {
    			return i;
    		}
    	}
    	return 0;
    }
    
    private int getLeftMostTile (int y) {
    	for (int i=0;i<maxX;i++) {
    		if (tiles[i][y] != 0 ) {
    			return i;
    		}
    	}
    	return 0;
    }
    
    private int getUpMostTile (int x) {
    	for (int j=0;j<maxY;j++) {
    		if (tiles[x][j] != 0 ) {
    			return j;
    		}
    	}
    	return 0;
    }
    
    private int getDownMostTile (int x) {
    	for (int j=maxY-1;j>=0;j--) {
    		if (tiles[x][j] != 0 ) {
    			return j;
    		}
    	}
    	return 0;
    }
    
    private void initStartPosition () {
    	curPosY = 0;
    	curPosX = getLeftMostTile(0);
    }
    
    private void play (int type) {
    	initStartPosition();
    	for (int i=0;i<moves.size();i++) {
    		String nextMove = moves.get(i);
    		if (nextMove.equals("R") ) {
    			turnAround(1);
    		}
    		else if (nextMove.equals("L")) {
    			turnAround(-1);
    		}
    		else {
    			if (type == 1) {
        			move(Integer.parseInt(nextMove));    				
    			}
    			else if (type == 2){
    				move2(Integer.parseInt(nextMove));
    			}
    		}
    	}
    }
    
    private void move (int moves) {
    	 for (int i=0;i<moves;i++) {
    		 int newX = curPosX + directions[dirIndex][0];
    		 int newY = curPosY + directions[dirIndex][1];
    		 
    		 if (newX<0 || newX>=maxX || newY<0 || newY>=maxY) {
        		 if (newX<0) {
        			 newX = getRightMostTile(curPosY);
        		 }
        		 if (newX>=maxX) {
        			 newX = getLeftMostTile(curPosY);
        		 }
        		 if (newY<0) {
        			 newY = getDownMostTile(curPosX);
        		 }
        		 if (newY>=maxY) {
        			 newY = getUpMostTile(curPosX);
        		 }    			 
    		 }
    		 else {
        		 if ((tiles[newX][newY] == 0 && dirIndex == 2 )) {
        			 newX = getRightMostTile(curPosY);
        		 }
        		 if ((tiles[newX][newY] == 0 && dirIndex == 0 )) {
        			 newX = getLeftMostTile(curPosY);
        		 }
        		 if ((tiles[newX][newY] == 0 && dirIndex == 3 )) {
        			 newY = getDownMostTile(curPosX);
        		 }
        		 if ((tiles[newX][newY] == 0 && dirIndex == 1 )) {
        			 newY = getUpMostTile(curPosX);
        		 }    			 
    		 }

    		 if (tiles[newX][newY]== '.') {
    			 curPosX = newX;
    			 curPosY = newY;
    		 }
    	 }
    }
    
    private void turnAround (int dirDiff) {
    	dirIndex += dirDiff;
    	if (dirIndex == directions.length) {
    		dirIndex = 0;
    	}
    	else if (dirIndex == -1) {
    		dirIndex = directions.length-1;
    	}
    }
    
    public int getScore(int type) {
    	play(type); 
    	System.out.println( (curPosY+1) + "," + (curPosX+1) + "," + dirIndex);
    	return ((curPosY+1) * 1000) + ((curPosX+1) * 4) + dirIndex;
    }
    
    private void move2 (int moves) {
   	 for (int i=0;i<moves;i++) {
		 int newX = curPosX + directions[dirIndex][0];
		 int newY = curPosY + directions[dirIndex][1];
		 int newDirIndex = dirIndex;
		 
		 /*
		 if (newX<0 || newX>=maxX || newY<0 || newY>=maxY) {
			 //5
    		 if (newX<0) {
    			newX = 19 - curPosY;
    			newY = 11;
    			newDirIndex = 3;
    		 }
    		 //3
    		 if (newX>=maxX) {
    			newX = 11;
    			newY = 11 - curPosY;
    			newDirIndex = 2;
    		 }
    		 //13
    		 if (newY<0) {
     			newX = 11 - curPosX;
     			newY = 4;
     			newDirIndex = 1;
    		 }
    		 if (newY>=maxY) {
    			 
    			 //9
    			 if (curPosX<12) {
    	     		newX = 11 - curPosX;
    	     		newY = 7;
    	     		newDirIndex = 3;			 
    			 }
    			 //10
    			 else {
    	     		newX = 0;
    	     		newY = 19 - curPosX;
    	     		newDirIndex = 0;				 
    			 }
    		 }    			 
		 }
		 else {
    		 if ((tiles[newX][newY] == 0 && dirIndex == 2 )) {
    			 //4
    			 if (curPosY < 4) {
     	     		newX = 4 + curPosY;
     	     		newY = 4;
     	     		newDirIndex = 1;				 			 
    			 }
    			 //6
    			 else {
      	     		newX = 15 - curPosY;
      	     		newY = 7;
      	     		newDirIndex = 3;				 			 
    			 }
    		 }
    		 if ((tiles[newX][newY] == 0 && dirIndex == 0 )) {
    			 //1
    			 if (curPosY < 4) {
      	     		newX = 15;
      	     		newY = 11 - curPosY;
      	     		newDirIndex = 2;				 			 
     			 }
     			 //2
     			 else {
       	     		newX = 19 - curPosY;
       	     		newY = 8;
       	     		newDirIndex = 1;				 			 
     			 }
    		 }
    		 if ((tiles[newX][newY] == 0 && dirIndex == 3 )) {
    			 //11
    			 if (curPosX < 4) {
      	     		newX = 11 - curPosX;
      	     		newY = 0;
      	     		newDirIndex = 1;				 			 
     			 }
    			 //12
    			 else if (curPosX < 8) {
       	     		newX = 8;
       	     		newY = curPosX - 4;
       	     		newDirIndex = 0;				 			 
      			 }
    			 //14
    			 else {
        	     		newX = 11;
           	     		newY = 19 - curPosX;
           	     		newDirIndex = 2;				 			 
    			 }
    		 }
    		 if ((tiles[newX][newY] == 0 && dirIndex == 1 )) {
    			 //7
    			 if (curPosX < 4) {
      	     		newX = 11 - curPosX;
      	     		newY = 11;
      	     		newDirIndex = 3;				 			 
     			 }
    			 //8
    			 else {
     	     		newX = 8;
     	     		newY = 15 - curPosX;
        	     	newDirIndex = 0;				 			 
    			 }
    		 }    			 
		 }
		 */


		if (newX<0 || newX>=maxX || newY<0 || newY>=maxY) {
    		 if (newX<0) {
    			 //7
    			 if (curPosY < 150) {
    					newX = 50;
    					newY = 149 - curPosY;
    					newDirIndex = 0;    				 
    			 }
    			 //8
    			 else {
    					newX = curPosY - 100;
    					newY = 0;
    					newDirIndex = 1;    				 
    			 }
    		 }
    		 if (newX>=maxX) {
    			 //1
				newX = 99;
				newY = 149 - curPosY;
				newDirIndex = 2;    				 		 
    		 }
    		 if (newY<0) {
    			 //13
    			 if (curPosX < 100) {
 					newX = 0;
 					newY = 100 + curPosX;
 					newDirIndex = 0;    				 
    			 }
    			 //14
    			 else {
 					newX = curPosX - 100;
 					newY = 199;
 					newDirIndex = 3;    				     				 
    			 }
    		 }
    		 if (newY>=maxY) {
    			 //9
				newX = curPosX+100;
				newY = 0;
				newDirIndex = 1;    				 		 
    		 }    			 
		 }
		 else {
    		 if ((tiles[newX][newY] == 0 && dirIndex == 2 )) {
    			 //5
    			 if (curPosY<50) {
    					newX = 0;
    					newY = 149 - curPosY;
    					newDirIndex = 0;    				 		 
    			 }
    			 //6
    			 else {
    					newX = curPosY - 50;
    					newY = 100;
    					newDirIndex = 1;    				 		 
    			 }
    		 }
    		 if ((tiles[newX][newY] == 0 && dirIndex == 0 )) {
    			 //2
    			 if (curPosY<100) {
 					newX = curPosY + 50;
 					newY = 49;
 					newDirIndex = 3;    				 		 
    			 }
    			 //3
    			 else if (curPosY < 150) {
 					newX = 149;
 					newY = 149 - curPosY;
 					newDirIndex = 2;    				 		 
    			 }
    			 //4
    			 else {
 					newX = curPosY- 100;
 					newY = 149;
 					newDirIndex = 3;    				 		 
    			 }
    		 }
    		 if ((tiles[newX][newY] == 0 && dirIndex == 3 )) {
    			 	//12
					newX = 50;
					newY = 50 + curPosX;
					newDirIndex = 0;    				 		 
    		 }
    		 if ((tiles[newX][newY] == 0 && dirIndex == 1 )) {
    			 //10
    			 if (curPosX<100) {
 					newX = 49;
 					newY = curPosX + 100;
 					newDirIndex = 2;    				 		 
    			 }
    			 //11
    			 else {
 					newX = 99;
 					newY = curPosX - 50;
 					newDirIndex = 2;    				 		 
    			 }
    		 }    			 
		 }

		 if (tiles[newX][newY]== '.') {
			 curPosX = newX;
			 curPosY = newY;
			 dirIndex = newDirIndex;
		 }
	 }

    }
    
    public static void main(String[] args) {
        try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[1]))) {
            Day22 day22 = (new Day22(in1));
            //System.out.println("====> Test Score part1 (6032?): " + day22.getScore(1));
            //System.out.println("====> Test Score part2 (5031?): " + day22.getScore(2));
            day22 = (new Day22(in2));
            System.out.println("====> Score part1 (31568?): " + day22.getScore(1));
            System.out.println("====> Score part2 (36540): " + day22.getScore(2));
        }
        catch (Exception ex) {
            System.out.println("Upps! Something went wrong!" + ex.getMessage());
            ex.printStackTrace();
        }
	}
}
