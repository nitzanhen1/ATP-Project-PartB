package algorithms.mazeGenerators;

public class Maze {
    private Position startPos;
    private Position goalPos;
    private int rowSize;
    private int columnSize;
    private int[][] maze;


    public Maze(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        maze = new int[rowSize][columnSize];

        startPos= new Position(0,0);
        goalPos= new Position(rowSize,columnSize);
    }

    public void print(){
        for(int i=0;i<rowSize;i++){
            String mazeStr="";
            mazeStr+="{ ";
            for(int j=0;j<columnSize;j++){
                if(i== startPos.getRowIndex()&& j== startPos.getColumnIndex())
                    mazeStr += "S ";
                else if(i== goalPos.getRowIndex()-1&& j== goalPos.getColumnIndex()-1)
                    mazeStr += "E ";
                else
                    mazeStr+= maze[i][j]+" ";
            }
            mazeStr+= "}";
            System.out.println(mazeStr);
        }
    }

    public Position getGoalPosition() {
        return goalPos;
    }

    public Position getStartPosition() {
        return startPos;
    }
}


