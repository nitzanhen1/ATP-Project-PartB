package algorithms.mazeGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    /*private int[] getIntNum(byte[] array, int i){
        int num=0;
        while (array[i]!=(byte) 0) {
            num += Byte.toUnsignedInt(array[i]);
            i++;
        }
        return new int[]{num,++i};
    }

    private void setAllMaze(byte[] mazeInByte,int idx) {
        int rowNum=0;
        int colNum=0;
        int num;
        boolean flag;
        while (rowNum<rowSize) {
            flag=false;
            while (colNum < columnSize) {
                num= Byte.toUnsignedInt(mazeInByte[idx]);
                while (num>0){
                    if (!flag)
                        setCell(rowNum,colNum,0);
                    else
                        setCell(rowNum,colNum,1);
                    colNum++;
                    num--;
                }
                flag= !flag;
                idx++;
            }
            colNum=0;
            rowNum++;
        }
    }*/


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


//compress
        /*int counter=0;
        boolean color=false;// false= o,(path). true = 1,(wall)
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<columnSize;j++){

                if(!color){
                    if(maze[i][j]==0){
                        counter++;
                    }
                    else{
                        addMazeToByteList(counter,listSizes);
                        counter=1;
                        color=true;
                    }
                }
                else{
                    if(maze[i][j]==1){
                        counter++;
                    }
                    else{
                        addMazeToByteList(counter,listSizes);
                        counter=1;
                        color=false;
                    }
                }
            }
            addMazeToByteList(counter, listSizes);
            counter=0;
            color=false;
        }*/

    }

    /*private void addMazeToByteList(int size,ArrayList<Byte> listSizes) {
        int counter=size;
        if(counter==0){
            listSizes.add((byte)0);
            return;
        }

        while(counter>0){
            if(counter>255){
                listSizes.add((byte)255);
                listSizes.add((byte)0);
            }
            else
                listSizes.add((byte)counter);
            counter=counter-255;
        }

    }*/

    private void addToByteList(int size,ArrayList<Byte> listSizes){
        listSizes.add((byte)(size/256));
        listSizes.add((byte)(size%256));

        /*int counter=size;
        while(counter>0){
            if(counter>255)
                listSizes.add((byte)255);
            else
                listSizes.add((byte)counter);
            counter=counter-255;
        }
        listSizes.add((byte)0);*/
    }

    /*public byte[] myByteArray(){
        ArrayList<Byte> listSizes = new ArrayList<>();
        addToByteList(rowSize,listSizes);
        addToByteList(columnSize,listSizes);
        addToByteList(startPosition.getRowIndex(),listSizes);
        addToByteList(startPosition.getColumnIndex(),listSizes);
        addToByteList(goalPosition.getRowIndex(),listSizes);
        addToByteList(goalPosition.getColumnIndex(),listSizes);

        int[] binaryArray = new int[8];
        int i=0;int j=0;int counter=0;
        while(i<rowSize){
            while(j<columnSize){
                if(counter<8) {
                    binaryArray[counter] = maze[i][j];
                    counter++;
                }
                else{

                }

            }
        }
    }*/

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
}


