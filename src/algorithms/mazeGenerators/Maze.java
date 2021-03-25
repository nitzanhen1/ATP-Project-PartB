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
        String mazeStr="";
        for(int i=0;i<rowSize;i++){
            mazeStr+="{ ";
            for(int j=0;j<columnSize;j++){
                if(i== startPos.getRowIndex()&& j== startPos.getColumnIndex())
                    mazeStr += "S ";
                else if(i== goalPos.getRowIndex()&& j== goalPos.getColumnIndex())
                    mazeStr += "E ";
                else
                    mazeStr+= maze[i][j]+" ";
            }
        }
            mazeStr+= "}\n";

        System.out.println(mazeStr);
    }

}


