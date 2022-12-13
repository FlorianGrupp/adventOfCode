package adventcode;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;

public class Day12Visualization extends Day12 {
	
	private char[][] fewestSteps;
	
	public Day12Visualization (LineNumberReader in, boolean scenic) throws Exception {
		super(in,scenic);
	}
	
	public void initFewestSteps () {
		fewestSteps = new char[minSteps.length][minSteps[0].length];
		markPath(destX,destY);
		fewestSteps[destX][destY] = 'E';
	}

	private void markPath (int x, int y) {
		if (minSteps[x][y] != 0) {
			if (minSteps[x][y] - 1 == getMinSteps(x+1,y) && isMovePossible(x,y,1,0)) {
				fewestSteps[x+1][y] = (char) heightMap[x+1][y];//'<';
				markPath(x+1,y);
			}
			else if (minSteps[x][y] - 1 == getMinSteps(x-1,y) && isMovePossible(x,y,-1,0)) {
				fewestSteps[x-1][y] = (char) heightMap[x-1][y];//'>';
				markPath(x-1,y);
			}
			else if (minSteps[x][y] - 1 == getMinSteps(x,y-1) && isMovePossible(x,y,0,-1)) {
				fewestSteps[x][y-1] = (char) heightMap[x][y-1];//'v';
				markPath(x,y-1);
			}
			else if (minSteps[x][y] - 1 == getMinSteps(x,y+1) && isMovePossible(x,y,0,1)) {
				fewestSteps[x][y+1] = (char) heightMap[x][y+1];//'^';
				markPath(x,y+1);
			}
		}
		else {
			fewestSteps[x][y] = 'S';			
		}
	}
	
	private int getMinSteps (int x, int y) {
		try {
			return minSteps[x][y];
		}
		catch (Exception ex) {
			return Integer.MAX_VALUE;
		}
	}
	
	public void printPath () {
		for (int j=0;j<fewestSteps[0].length;j++) {
			for (int i=0;i<fewestSteps.length;i++) {
				if (fewestSteps[i][j] == 0) {
					System.out.print(".");
				}
				else {
					System.out.print(fewestSteps[i][j]);					
				}
			}
			System.out.println();
		}
		System.out.println();		
	}

	public String toHTMLString () {
		StringBuilder sb = new StringBuilder();
		sb.append("<table class=\"field\">");
		for (int j=0;j<fewestSteps[0].length;j++) {
			sb.append("<tr>");
			for (int i=0;i<fewestSteps.length;i++) {
				sb.append(getTD(heightMap[i][j],fewestSteps[i][j]));
			}
			sb.append("</tr>");
		}		
		sb.append("</table>");
		return sb.toString();
	}
	
	public String toXLSString () {
		StringBuilder sb = new StringBuilder();
		for (int j=0;j<heightMap[0].length;j++) {
			for (int i=0;i<heightMap.length;i++) {
				sb.append((heightMap[i][j]-97) + "\t");
			}
			sb.append("\n");
		}		
		return sb.toString();	
	}
	
	private String getTD(int height, char c) {
		String s = "&nbsp;&nbsp;&nbsp;";
		if (c != 0 ) {
			s = c + "";
		}
		return "<td class=\"field\" style=\"background-color:" + getColor(height) + "\">" + s + "</td>";
	}
	
	private String getColor(int height) {
		int normalized = ((height - 97)*10) + 6;
		return "#" + toHex(normalized) + toHex(normalized) + toHex(normalized);
	}
	
	private String toHex(int i) {
		String ret = Integer.toHexString(i);
		if (ret.length()==1) {
			ret = "0" + ret;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[0]));FileWriter writer = new FileWriter(args[1])) {
			Day12Visualization day12 = (new Day12Visualization(in1,false));
			System.out.println("Score part1: "  + day12.minimumReach());
			day12.initFewestSteps();
			day12.printPath();
			writer.write("<!DOCTYPE html><html lang=\"en\"> <head><link rel=\"stylesheet\" href=\"heatmap.css\"></link><title>Heatmap AoC Day12</title></head><body><p><h1>No scenic</h1>");
			writer.write(day12.toHTMLString());
			
			day12 = (new Day12Visualization(in2,true));
			System.out.println("Score part2: "  + day12.minimumReach());
			day12.initFewestSteps();
			day12.printPath();
			writer.write("<h1>Scenic</h1>");
			writer.write(day12.toHTMLString());
			writer.write("</p></body></html>");
			writer.close();
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
	
}
