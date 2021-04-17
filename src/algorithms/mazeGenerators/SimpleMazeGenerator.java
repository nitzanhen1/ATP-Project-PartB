package algorithms.mazeGenerators;
import java.util.Random;
public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * params rowSize,columnSize- determine the size of the maze
     * @return simple maze -all cells values are chosen at random (0,1), then carving a path from start to goal position
     */

    @Override
    public Maze generate(int rowSize, int columnSize) {

        Maze maze = new Maze(rowSize,columnSize);

        Random rand = new Random();
        int num;
        //randomly allocates values to the maze cells
        for(int i = 0; i < rowSize; i++){
            for(int j = 0; j < columnSize; j++){
                num =rand.nextInt(2);
                maze.setCell(i,j,num);
            }
        }
        //carving path fro start to goal- in the shape of 'stairs'
        int currRow = maze.getStartPosition().getRowIndex();
        int currCol = maze.getStartPosition().getColumnIndex();
        int gRow = maze.getGoalPosition().getRowIndex();
        int gCol = maze.getGoalPosition().getColumnIndex();

        maze.setCell(gRow,gCol,0);

        while(currRow != gRow || currCol != gCol){
            maze.setCell(currRow,currCol,0);
            if(currRow-gRow >0)
                currRow--;
            else if(currRow-gRow <0)
                currRow++;
            maze.setCell(currRow,currCol,0);
            if(currCol-gCol>0)
                currCol--;
            else if(currCol-gCol<0)
                currCol++;
        }

        return maze;
    }
}
