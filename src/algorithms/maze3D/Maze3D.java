package algorithms.maze3D;

import java.util.Random;

public class Maze3D {
    private int[][][] maze;
    private Position3D startPos;
    private Position3D goalPos;
    private int depthSize;
    private int rowSize;
    private int columnSize;


    /** create 3D maze with sizes of params
     ** create start and goal position randomly
     */
    public Maze3D(int depthSize, int rowSize, int columnSize) throws IllegalArgumentException{
        if(depthSize<2 || rowSize<2 || columnSize<2)
            throw new IllegalArgumentException("min size of maze is 2x2x2");
        this.depthSize = depthSize;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        maze = new int[depthSize][rowSize][columnSize];

        Random rand = new Random();
        //start pos is bound to lower half of depthSize and to the first column
        startPos =  new Position3D(rand.nextInt(depthSize/2),rand.nextInt(rowSize),0);
        //start pos is bound to upper half of depthSize and to the last column
        goalPos =  new Position3D(rand.nextInt(depthSize/2)+depthSize/2,rand.nextInt(rowSize),columnSize-1);
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

    //if cell is in maze boundaries, return it's value, else return -1
    protected int getCell(int depth, int row, int column){
        if(depth<0 || depth>=depthSize|| row<0 || row>=rowSize|| column<0 || column>=columnSize)
            return -1;
        return maze[depth][row][column];
    }

    //if cell is in maze boundaries, and value is 0 or 1 set it, else don't make changes
    protected void setCell(int depth, int row, int column, int val){
        if(getCell(depth,row,column)==-1|| val<0 ||val >1)
            return;
        maze[depth][row][column]=val;
    }
}
