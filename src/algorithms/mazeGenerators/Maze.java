package algorithms.mazeGenerators;

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
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        maze = new int[rowSize][columnSize];

        startPos= new Position(0,0);
        goalPos= new Position(rowSize-1,columnSize-1);
    }

    protected void setCell(int row, int column, int val){
        if(row<0 || row>=rowSize|| column<0 || column>=columnSize|| val<0 ||val >1)
            return;
        maze[row][column]=val;
    }

    public void print(){
        String mazeStr="";
        for(int i=0;i<rowSize;i++){
            mazeStr="";
            mazeStr+="{ ";
            for(int j=0;j<columnSize;j++){
                if(i== startPos.getRowIndex()&& j== startPos.getColumnIndex())
                    mazeStr += "S ";
                else if(i== goalPos.getRowIndex()&& j== goalPos.getColumnIndex())
                    mazeStr += "E ";
                else
                    mazeStr = mazeStr+ maze[i][j]+" ";
            }
            mazeStr+= "}";
            System.out.println(mazeStr);
        }
    }


}


