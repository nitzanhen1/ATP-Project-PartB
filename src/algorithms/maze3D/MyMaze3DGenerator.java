package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;

import java.util.LinkedList;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator {

    @Override
    public Maze3D generate(int depthSize, int rowSize, int colSize){
    /*
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

        while ( !passages.isEmpty() ){
            int[] nextPath = passages.remove( rand.nextInt( passages.size() ) );
            row = nextPath[2];
            col = nextPath[3];
            if ( maze.getCell(row,col) == 1 )
            {
                maze.setCell(nextPath[0],nextPath[1],0);
                maze.setCell(row,col,0);
                if (maze.getCell(row-2,col) == 1 )
                    passages.add( new int[]{row-1,col,row-2,col} );
                if (maze.getCell(row,col-2) == 1 )
                    passages.add( new int[]{row,col-1,row,col-2} );
                if (maze.getCell(row+2,col) == 1 )
                    passages.add( new int[]{row+1,col,row+2,col} );
                if (maze.getCell(row,col+2) == 1 )
                    passages.add( new int[]{row,col+1,row,col+2} );
            }
        }

        row = maze.getGoalPosition().getRowIndex();
        col = maze.getGoalPosition().getColumnIndex();
        if(maze.getCell(row,col)==1) {
            maze.setCell(row, col, 0);
            if(maze.getCell(row-1,col) != 0 && maze.getCell(row+1,col) != 0 && maze.getCell(row,col-1) != 0 && maze.getCell(row,col+1) != 0)
                maze.setCell(row-1,col, 0);
        }


        return maze;*/
        return new Maze3D(depthSize,rowSize,colSize);
    }
}
