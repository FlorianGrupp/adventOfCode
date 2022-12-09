package adventcode;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;

public class Day8Visualization extends Day8 {
	
	public Day8Visualization (LineNumberReader in) throws Exception {
		super(in);
	}
	
	public String toString (String mode, String format) {
		StringBuilder sb = new StringBuilder();
		
		int[][] matrix = trees;
		if (mode.equals("Score1")) {
			matrix = score1Matrix;
		}
		if (mode.equals("Score2")) {
			matrix = score2Matrix;
		}
		
		for (int j=0;j<matrix[0].length;j++) {
			if (format.equals("html")) {
				sb.append("<tr height=\"5px\">");
			}
			for (int i=0;i<matrix.length;i++) {
				if (mode.equals("TreeHeight") && format.equals("text")) {
					sb.append(matrix[i][j]);
				}
				else if (mode.equals("Score1") && format.equals("text")) {
					if (matrix[i][j] == 0) {
						sb.append(" ");						
					}
					else {
						sb.append("*");
					}
				}
				else if (mode.equals("Score2") && format.equals("text")) {
					sb.append("("+ ((int)Math.max(0,Math.log( matrix[i][j])* 256 / 13)) + ")");
				}
				else if (mode.equals("TreeHeight") && format.equals("html")) {
					sb.append(getTD( (int) (matrix[i][j]) * 256 / 9) );
				}
				else if (mode.equals("Score1") && format.equals("html")) {
					if (matrix[i][j] == 0) {
						sb.append(getTD(256));						
					}
					else {
						sb.append(getTD(0));
					}
				}
				else if (mode.equals("Score2") && format.equals("html")) {
					if (matrix[i][j] == score2) {
						sb.append("<td width=\"4px\" style=\"background-color:red\"></td>");
					}
					else {
						sb.append(getTD((int)Math.max(0,Math.log( matrix[i][j])* 256 / 13)));
					}
				}
				if (mode.equals("TreeHeight") && format.equals("xls")) {
					sb.append(matrix[i][j] + "\t");
				}
				else if (mode.equals("Score1") && format.equals("xls")) {
					if (matrix[i][j] == 0) {
						sb.append("0\t");						
					}
					else {
						sb.append("1\t");
					}
				}
				else if (mode.equals("Score2") && format.equals("xls")) {
					sb.append(((int)Math.max(0,Math.log( matrix[i][j]))) + "\t");
					//sb.append(matrix[i][j] + "\t");
				}
			}
			if (format.equals("html")) {
				sb.append("</tr>");
			}
			sb.append("\r\n");
		}
		return sb.toString();
	}

	private String getTD(int color) {
		return "<td width=\"4px\" style=\"background-color:#" + Integer.toHexString(color) + Integer.toHexString(color) + Integer.toHexString(color) + "\"></td>";
	}
	
	public static void main(String[] args) {
		try (LineNumberReader in = new LineNumberReader(new FileReader(args[0]));FileWriter writer = new FileWriter(args[1])) {
			Day8Visualization day8 = new Day8Visualization(in);
			writer.write(day8.toString("TreeHeight", "text"));
		}
		catch (Exception ex) {
			System.out.println("Upps! Something went wrong!" + ex.getMessage());
		}
	}
}
