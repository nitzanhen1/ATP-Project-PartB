package algorithms.mazeGenerators;
import java.util.Random;
public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rowSize, int columnSize) {

        Maze maze = new Maze(rowSize,columnSize);

        Random rand = new Random();
        int num;
        for(int i = 0; i < rowSize; i++){
            for(int j = 0; j < columnSize; j++){
                num =rand.nextInt(2);
                maze.setCell(i,j,num);
            }
        }

        //test
        maze.print();
        System.out.println("***\n\n***");

        int currRow = maze.getStartPosition().getRowIndex();
        int currCol = maze.getStartPosition().getColumnIndex();
        int gRow = maze.getGoalPosition().getRowIndex();
        int gCol = maze.getGoalPosition().getColumnIndex();

        maze.setCell(gRow,gCol,0);

        while(currRow != gRow && currCol != gCol){
            maze.setCell(currRow,currCol,0);
            if(currRow-gRow >0)
                currRow--;
            else
                currRow++;
            maze.setCell(currRow,currCol,0);
            if(currCol-gCol>0)
                currCol--;
            else
                currCol++;
        }

        return maze;
    }
}
