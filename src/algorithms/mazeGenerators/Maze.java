package algorithms.mazeGenerators;
import java.io.Serializable;
import java.util.*;

public class Maze implements Serializable {
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

    public Maze(byte[] mazeInByte){
        if(mazeInByte==null)
            throw new IllegalArgumentException("expected byte array, received null");

        this.rowSize= Byte.toUnsignedInt(mazeInByte[0])*256+Byte.toUnsignedInt(mazeInByte[1]);
        this.columnSize= Byte.toUnsignedInt(mazeInByte[2])*256+Byte.toUnsignedInt(mazeInByte[3]);
        this.maze = new int[rowSize][columnSize];

        int row= Byte.toUnsignedInt(mazeInByte[4])*256+Byte.toUnsignedInt(mazeInByte[5]);
        int col= Byte.toUnsignedInt(mazeInByte[6])*256+Byte.toUnsignedInt(mazeInByte[7]);
        startPosition= new Position(row,col);

        row= Byte.toUnsignedInt(mazeInByte[8])*256+Byte.toUnsignedInt(mazeInByte[9]);
        col= Byte.toUnsignedInt(mazeInByte[10])*256+Byte.toUnsignedInt(mazeInByte[11]);
        goalPosition= new Position(row,col);

        for (int i=0;i<rowSize;i++){
            for(int j=0;j<columnSize;j++)
                maze[i][j]=mazeInByte[i*columnSize+j+12];
        }
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

    public byte[] toByteArray(){

        ArrayList<Byte> listSizes = new ArrayList<>();
        addToByteList(rowSize,listSizes);
        addToByteList(columnSize,listSizes);
        addToByteList(startPosition.getRowIndex(),listSizes);
        addToByteList(startPosition.getColumnIndex(),listSizes);
        addToByteList(goalPosition.getRowIndex(),listSizes);
        addToByteList(goalPosition.getColumnIndex(),listSizes);

        byte[] byteArray = new byte[rowSize*columnSize+12];

        for(int i=0;i<12;i++){
            byteArray[i]=listSizes.get(i);
        }

        for(int i=0;i<rowSize;i++) {
            for(int j=0;j<columnSize;j++) {
                byteArray[i * columnSize + j + 12] = (byte) maze[i][j];
            }
        }

        return byteArray;
    }

    private void addToByteList(int size,ArrayList<Byte> listSizes){
        listSizes.add((byte)(size/256));
        listSizes.add((byte)(size%256));
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

    public int[][] getMaze() {
        return maze;
    }

    public static final char WALL_CHAR = '▓';
    public String toString(){

        final StringBuffer b = new StringBuffer();
        for ( int x = 0; x < rowSize ; x++ ){
            for ( int y = 0; y < columnSize; y++ ){
                if(x==startPosition.getRowIndex()&&y==startPosition.getColumnIndex())
                    b.append("S ");
                else if(x==goalPosition.getRowIndex()&&y==goalPosition.getColumnIndex())
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze1 = (Maze) o;
        return rowSize == maze1.rowSize && columnSize == maze1.columnSize && Objects.equals(startPosition, maze1.startPosition) && Objects.equals(goalPosition, maze1.goalPosition) && Arrays.equals(maze, maze1.maze);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(startPosition, goalPosition, rowSize, columnSize);
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++)
                result += maze[i][j] * (i * columnSize + j);
        }
        return result;
    }

}


