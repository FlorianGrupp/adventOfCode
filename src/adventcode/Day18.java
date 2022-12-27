package adventcode;

import java.io.FileReader;

import java.io.LineNumberReader;

import java.util.*;

public class Day18 {

	private int maxX;
    private int maxY;
    private int maxZ;
    private static final int[][] directions = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};

    private boolean[][][] cube;

    public Day18 (LineNumberReader in) throws Exception {
        ArrayList<int[]> coords = new ArrayList<>();
        String line = in.readLine();
        maxX = Integer.MIN_VALUE;
        maxY = Integer.MIN_VALUE;
        maxZ = Integer.MIN_VALUE;

        while (line != null) {
            String[] split = line.split(",");
            int[] temp = new int[3];
            temp[0] = Integer.parseInt(split[0]);
            temp[1] = Integer.parseInt(split[1]);
            temp[2] = Integer.parseInt(split[2]);

            maxX = Math.max(maxX, temp[0]);
            maxY = Math.max(maxY, temp[1]);
            maxZ = Math.max(maxZ, temp[2]);

            coords.add(temp);

            line = in.readLine();
        }

        //add additional air layer for easy filling in part2
        cube = new boolean[maxX+3][maxY+3][maxZ+3];

        Iterator<int[]> iter = coords.iterator();
        while (iter.hasNext()) {
            int[] temp = iter.next();
            cube[temp[0]+1][temp[1]+1][temp[2]+1] = true;
        }
    }

    public int getScore1 () {
       return calculateSurface();
    }

    private int calculateSurface () {
        int score =0;
        for (int i=0;i<=maxX+2;i++) {
            for (int j=0;j<=maxY+2;j++) {
                for (int k=0;k<=maxZ+2;k++) {
                    if (cube[i][j][k]) {
                          for (int l=0;l<directions.length;l++) {
                            score += hasSurface(i,j,k,directions[l]);                           
                          }
                    }
                }
            }
        }
        return score;
    }

    private void fillCube (int x, int y, int z) {
       //outside of cube
       if (x<0 || y<0 || z<0 || x > maxX+2 || y > maxY+2 || z > maxZ+2) {
             return;
       }

       //already filled?
       if (!cube[x][y][z]) {
             cube[x][y][z] = true;
             for (int i=0;i<directions.length;i++) {
                    fillCube(x+directions[i][0],y+directions[i][1],z+directions[i][2]);                      
             }
       }
    }

    public int getScore2 () {
       int surfaceBeforeFill = calculateSurface();
       fillCube(0,0,0);
       return surfaceBeforeFill - calculateSurface();
    }

    private int hasSurface (int x, int y, int z, int[] delta) {
       //outside cube
        if ( x+delta[0] <0 || y+delta[1] <0 || z+delta[2] <0 || x+delta[0] >maxX+2 || y+delta[1] >maxY+2 || z+delta[2] >maxZ+2) {
             return 0;
        }

        //neighboring position free
       if(!cube[x+delta[0]][y+delta[1]][z+delta[2]]) {
            return 1;
       }
       return 0;
    }

    public static void main(String[] args) {
        try (LineNumberReader in1 = new LineNumberReader(new FileReader(args[0]));LineNumberReader in2 = new LineNumberReader(new FileReader(args[1]))) {
            Day18 day18 = (new Day18(in1));
            System.out.println("====> Test Score part1 (64?): " + day18.getScore1());
            System.out.println("====> Test Score part2 (58?): " + day18.getScore2());
            day18 = (new Day18(in2));
            System.out.println("====> Score part1 (3364?): " + day18.getScore1());
            System.out.println("====> Score part2 (2006?): " + day18.getScore2());
        }
        catch (Exception ex) {
            System.out.println("Upps! Something went wrong!" + ex.getMessage());
        }
    }
}