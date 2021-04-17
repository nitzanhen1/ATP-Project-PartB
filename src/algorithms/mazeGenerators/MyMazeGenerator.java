package algorithms.mazeGenerators;
import algorithms.maze3D.Maze3D;

import java.util.LinkedList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rowSize, int columnSize) {
        //prim's algorithm
        Maze maze = new Maze(rowSize,columnSize);
        for (int i=0; i<rowSize; i++){
            for (int j=0;j<columnSize; j++)
                maze.setCell(i,j,1);
        }

        LinkedList<int[]> passages = new LinkedList<>();
        Random rand = new Random();

        int row = maze.getStartPosition().getRowIndex();
        int col = maze.getStartPosition().getColumnIndex();
        passages.add(new int[]{row,col,row,col});
        int[] nextPath;

        while ( !passages.isEmpty() ){
            nextPath = passages.remove( rand.nextInt( passages.size() ) );
            row = nextPath[2];
            col = nextPath[3];
            if ( maze.getCell(row,col) == 1 )
            {
                maze.setCell(nextPath[0],nextPath[1],0);
                maze.setCell(row,col,0);

                passages.addAll(addNeighboringPassages(maze,row,col));
            }
        }
        this.createPathForGoal(maze);
        return maze;
    }



    private LinkedList<int[]> addNeighboringPassages(Maze maze , int row , int col){
        LinkedList<int[]> passages = new LinkedList<>();

        if (maze.getCell(row-2,col) == 1 )
            passages.add( new int[]{row-1,col,row-2,col} );
        if (maze.getCell(row,col-2) == 1 )
            passages.add( new int[]{row,col-1,row,col-2} );
        if (maze.getCell(row+2,col) == 1 )
            passages.add( new int[]{row+1,col,row+2,col} );
        if (maze.getCell(row,col+2) == 1 )
            passages.add( new int[]{row,col+1,row,col+2} );

        return passages;
    }

    private void createPathForGoal(Maze maze) {
        int row = maze.getGoalPosition().getRowIndex();
        int col = maze.getGoalPosition().getColumnIndex();
        if(maze.getCell(row,col)==1) {
            maze.setCell(row, col, 0);
            if (maze.getCell(row - 1, col) != 0 && maze.getCell(row + 1, col) != 0 && maze.getCell(row, col - 1) != 0 && maze.getCell(row, col + 1) != 0)
                maze.setCell(row - 1, col, 0);
        }
    }
}
