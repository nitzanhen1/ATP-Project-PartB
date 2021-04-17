package algorithms.mazeGenerators;
import java.util.Random;

public class Maze {
    private Position startPosition;
    private Position goalPosition;
    private int rowSize;
    private int columnSize;
    private int[][] maze;

    /** create maze with sizes of params
     ** create start and goal position randomly
     */
    public Maze(int rowSize, int columnSize) throws IllegalArgumentException{
        if(rowSize<2 || columnSize<2)
            throw new IllegalArgumentException("min size of maze is 2x2");
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        maze = new int[rowSize][columnSize];

        Random rand = new Random();
        //start pos is bound to lower half of rowSize and to the first column
        startPosition= new Position(rand.nextInt(rowSize/2),0);
        //start pos is bound to upper half of rowSize and to the last column
        goalPosition= new Position(rand.nextInt(rowSize/2)+rowSize/2,columnSize-1);
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    //if cell is in maze boundaries, return it's value, else return -1
    public int getCell(int row, int column){
        if(row<0 || row>=rowSize|| column<0 || column>=columnSize)
            return -1;
        return maze[row][column];
    }

    //if cell is in maze boundaries, and value is 0 or 1 set it, else don't make changes
    protected void setCell(int row, int column, int val){
        if(getCell(row,column)==-1|| val<0 ||val >1)
            return;
        maze[row][column]=val;
    }

    public void print(){
        String mazeStr;
        for(int i=0;i<rowSize;i++){
            mazeStr="";
            mazeStr+="{ ";
            for(int j=0;j<columnSize;j++){
                if(i== startPosition.getRowIndex()&& j== startPosition.getColumnIndex())
                    mazeStr += "S";
                else if(i== goalPosition.getRowIndex()&& j== goalPosition.getColumnIndex())
                    mazeStr += "E";
                else
                    mazeStr += maze[i][j];
                mazeStr+=" ";
            }
            mazeStr+= "}";
            System.out.println(mazeStr);
        }
    }
}


