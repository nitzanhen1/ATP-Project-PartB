package algorithms.mazeGenerators;
import java.util.Random;
public class Maze {
    private Position startPos;
    private Position goalPos;
    private int rowSize;
    private int columnSize;
    private int[][] maze;

    public Position getGoalPosition() {
        return goalPos;
    }

    public Position getStartPosition() {
        return startPos;
    }

    public Maze(int rowSize, int columnSize) {
        if(rowSize<2 || columnSize<2)
            throw new IllegalArgumentException("min size of maze is 2x2");
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        maze = new int[rowSize][columnSize];

        Random rand = new Random();

        startPos= new Position(rand.nextInt(rowSize/2),0);
        goalPos= new Position(rand.nextInt(rowSize/2)+rowSize/2,columnSize-1);
    }

    protected void setCell(int row, int column, int val){
        if(row<0 || row>=rowSize|| column<0 || column>=columnSize|| val<0 ||val >1)
            return;
        maze[row][column]=val;
    }

    public int getCell(int row, int column){
        if(row<0 || row>=rowSize|| column<0 || column>=columnSize)
            return -1;
        return maze[row][column];
    }

    public void print(){
        String mazeStr;
        for(int i=0;i<rowSize;i++){
            mazeStr="";
            mazeStr+="{ ";
            for(int j=0;j<columnSize;j++){
                if(i== startPos.getRowIndex()&& j== startPos.getColumnIndex())
                    mazeStr += "S";
                else if(i== goalPos.getRowIndex()&& j== goalPos.getColumnIndex())
                    mazeStr += "E";
                else
                    mazeStr += maze[i][j];
                mazeStr+=" ";
            }
            mazeStr+= "}";
            System.out.println(mazeStr);
        }
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
        for ( int x = 0; x < rowSize ; x++ ){
            for ( int y = 0; y < columnSize; y++ ){
                if(x==startPos.getRowIndex()&&y==startPos.getColumnIndex())
                    b.append("S ");
                else if(x==goalPos.getRowIndex()&&y==goalPos.getColumnIndex())
                    b.append("E ");
                else if(maze[x][y] == 1){
                    b.append( WALL_CHAR );
                    b.append( WALL_CHAR );
                }
                else
                    b.append("  ");
            }
            b.append( '\n' );
        }
        return b.toString();
    }


}


