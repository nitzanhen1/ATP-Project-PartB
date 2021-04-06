package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;

import java.util.LinkedList;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator {

    @Override
    public Maze3D generate(int depthSize, int rowSize, int colSize){

        //prim's algorithm
        Maze3D maze = new Maze3D(depthSize, rowSize,colSize);
        for (int i=0; i<depthSize; i++){
            for (int j=0;j<rowSize; j++) {
                for (int k = 0; k < colSize; k++)
                    maze.setCell(i, j, k,1);
            }
        }
        LinkedList<int[]> passages = new LinkedList<>();
        Random rand = new Random();
        int depth = maze.getStartPosition().getDepthIndex();
        int row = maze.getStartPosition().getRowIndex();
        int col = maze.getStartPosition().getColumnIndex();
        passages.add(new int[]{depth,row,col,depth,row,col});
        int[] nextPath;

        while ( !passages.isEmpty() ){
            if(passages.size() < 1000)
                nextPath = passages.remove(rand.nextInt(passages.size()));
            else
                nextPath = passages.remove(rand.nextInt((int)Math.cbrt(passages.size())));
            depth = nextPath[3];
            row = nextPath[4];
            col = nextPath[5];
            if ( maze.getCell(depth,row,col) == 1 )
            {
                maze.setCell(nextPath[0],nextPath[1],nextPath[2],0);
                maze.setCell(depth,row,col,0);

                if(maze.getCell(depth-1,row,col)==1)
                    passages.add((new int[]{depth,row,col,depth-1,row,col}));
                if(maze.getCell(depth,row-2,col)==1)
                    passages.add((new int[]{depth,row-1,col,depth,row-2,col}));
                if(maze.getCell(depth,row,col-2)==1)
                    passages.add((new int[]{depth,row,col-1,depth,row,col-2}));
                if(maze.getCell(depth+1,row,col)==1)
                    passages.add((new int[]{depth,row,col,depth+1,row,col}));
                if(maze.getCell(depth,row+2,col)==1)
                    passages.add((new int[]{depth,row+1,col,depth,row+2,col}));
                if(maze.getCell(depth,row,col+2)==1)
                    passages.add((new int[]{depth,row,col+1,depth,row,col+2}));
            }
        }
        depth = maze.getGoalPosition().getDepthIndex();
        row = maze.getGoalPosition().getRowIndex();
        col = maze.getGoalPosition().getColumnIndex();
        if(maze.getCell(depth,row,col)==1) {
            maze.setCell(depth,row, col, 0);

            maze.setCell(depth+1,row, col, 0);
            maze.setCell(depth-1,row, col, 0);
            maze.setCell(depth,row+1, col, 0);
            maze.setCell(depth,row-1, col, 0);
            maze.setCell(depth,row, col+1, 0);
            maze.setCell(depth,row, col-1, 0);

        }

        return maze;
    }

    @Override
    public Maze3D generate2(int depthSize, int rowSize, int colSize){

        //prim's algorithm
        Maze3D maze = new Maze3D(depthSize, rowSize,colSize);
        for (int i=0; i<depthSize; i++){
            for (int j=0;j<rowSize; j++) {
                for (int k = 0; k < colSize; k++)
                    maze.setCell(i, j, k,1);
            }
        }
        LinkedList<int[]> passages = new LinkedList<>();
        Random rand = new Random();
        int depth = maze.getStartPosition().getDepthIndex();
        int row = maze.getStartPosition().getRowIndex();
        int col = maze.getStartPosition().getColumnIndex();
        passages.add(new int[]{depth,row,col,depth,row,col});
        int[] nextPath;

        while ( !passages.isEmpty() ){
            if(passages.size() < 1000)
                nextPath = passages.remove(rand.nextInt(passages.size()));
            else
                nextPath = passages.remove(rand.nextInt((int)Math.cbrt(passages.size())));
            depth = nextPath[3];
            row = nextPath[4];
            col = nextPath[5];
            if ( maze.getCell(depth,row,col) == 1 )
            {
                maze.setCell(nextPath[0],nextPath[1],nextPath[2],0);
                maze.setCell(depth,row,col,0);

                if(maze.getCell(depth-2,row,col)==1)
                    passages.add((new int[]{depth-1,row,col,depth-2,row,col}));
                if(maze.getCell(depth,row-2,col)==1)
                    passages.add((new int[]{depth,row-1,col,depth,row-2,col}));
                if(maze.getCell(depth,row,col-2)==1)
                    passages.add((new int[]{depth,row,col-1,depth,row,col-2}));
                if(maze.getCell(depth+2,row,col)==1)
                    passages.add((new int[]{depth+1,row,col,depth+2,row,col}));
                if(maze.getCell(depth,row+2,col)==1)
                    passages.add((new int[]{depth,row+1,col,depth,row+2,col}));
                if(maze.getCell(depth,row,col+2)==1)
                    passages.add((new int[]{depth,row,col+1,depth,row,col+2}));
            }
        }
        depth = maze.getGoalPosition().getDepthIndex();
        row = maze.getGoalPosition().getRowIndex();
        col = maze.getGoalPosition().getColumnIndex();
        if(maze.getCell(depth,row,col)==1) {
            maze.setCell(depth,row, col, 0);

            maze.setCell(depth+1,row, col, 0);
            maze.setCell(depth-1,row, col, 0);
            maze.setCell(depth,row+1, col, 0);
            maze.setCell(depth,row-1, col, 0);
            maze.setCell(depth,row, col+1, 0);
            maze.setCell(depth,row, col-1, 0);

        }

        return maze;
    }
}
