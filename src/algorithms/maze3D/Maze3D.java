package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

import java.util.Random;

public class Maze3D {
    private int[][][] maze;
    private Position3D startPos;
    private Position3D goalPos;
    private int depthSize;
    private int rowSize;
    private int columnSize;


    public Maze3D(int depthSize, int rowSize, int columnSize) {
        if(depthSize<2 || rowSize<2 || columnSize<2)
            throw new IllegalArgumentException("min size of maze is 2x2x2");
        this.depthSize = depthSize;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        maze = new int[depthSize][rowSize][columnSize];

        Random rand = new Random();
        startPos =  new Position3D(rand.nextInt(depthSize),rand.nextInt(rowSize),0);
        goalPos =  new Position3D(rand.nextInt(depthSize),rand.nextInt(rowSize),columnSize-1);
    }

    public int[][][] getMap(){
        return maze;
    }

    public Position3D getStartPosition() {
        return startPos;
    }

    public Position3D getGoalPosition() {
        return goalPos;
    }

    public void print(){
        String mazeStr;
        String dashes="";
        int length = 2*columnSize+3;
        for(int i = 0; i < length; i++){
            dashes+= "-";
        }
        System.out.println("{");
        for(int i = 0; i < depthSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                mazeStr = "";
                mazeStr += "{ ";
                for (int k = 0; k < columnSize; k++) {
                    if (i == startPos.getDepthIndex() && j == startPos.getRowIndex() && k == startPos.getColumnIndex())
                        mazeStr += "S";
                    else if (i == goalPos.getDepthIndex() &&j == goalPos.getRowIndex() && k == goalPos.getColumnIndex())
                        mazeStr += "E";
                    else
                        mazeStr += maze[i][j][k];
                    mazeStr += " ";
                }
                mazeStr += "}";
                System.out.println(mazeStr);
            }
            if(i != depthSize-1)
                System.out.println(dashes);
        }
        System.out.println("}");
    }

    protected void setCell(int depth, int row, int column, int val){
        if(depth<0 || depth>=depthSize|| row<0 || row>=rowSize|| column<0 || column>=columnSize|| val<0 ||val >1)
            return;
        maze[depth][row][column]=val;
    }

    public int getCell(int depth, int row, int column){
        if(depth<0 || depth>=depthSize|| row<0 || row>=rowSize|| column<0 || column>=columnSize)
            return -1;
        return maze[depth][row][column];
    }


    public int getDepthSize() {
        return depthSize;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public static final char WALL_CHAR = '▓';
    public String toString(){

        final StringBuffer b = new StringBuffer();
        for(int z = 0; z < depthSize ; z++) {
            for (int x = 0; x < rowSize; x++) {
                for (int y = 0; y < columnSize; y++) {
                    if (z == startPos.getDepthIndex() && x == startPos.getRowIndex() && y == startPos.getColumnIndex())
                        b.append("S ");
                    else if (z == goalPos.getDepthIndex() && x == goalPos.getRowIndex() && y == goalPos.getColumnIndex())
                        b.append("E ");
                    else if (maze[z][x][y] == 1) {
                        b.append(WALL_CHAR);
                        b.append(WALL_CHAR);
                    } else
                        b.append("  ");
                }
                b.append('\n');
            }
            b.append("---------------\n");
        }
        return b.toString();
    }
}
