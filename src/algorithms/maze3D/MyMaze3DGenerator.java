package algorithms.maze3D;
import java.util.LinkedList;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator {

    /** generate maze using prims algorithm
     * for each current cell the algorithm evaluate 6 neighboring cells as describes below
     * return Maze3D*/
    @Override
    public Maze3D generate(int depthSize, int rowSize, int colSize){

        //initialize maze map to be all walls (val=1)
        Maze3D maze = new Maze3D(depthSize, rowSize,colSize);
        for (int i=0; i<depthSize; i++){
            for (int j=0;j<rowSize; j++) {
                for (int k = 0; k < colSize; k++)
                    maze.setCell(i, j, k,1);
            }
        }

        //passages holds all the cells that can potentially be part of the path in the maze (val=0)
        LinkedList<int[]> passages = new LinkedList<>();
        Random rand = new Random();

        //adding start position to passages
        int depth = maze.getStartPosition().getDepthIndex();
        int row = maze.getStartPosition().getRowIndex();
        int col = maze.getStartPosition().getColumnIndex();
        passages.add(new int[]{depth,row,col,depth,row,col});
        int[] nextPath;


        while ( !passages.isEmpty() ){
            //choose random 'nextPath' in passages
            //this condition improves runTime of large maze problem.
            if(passages.size() < 1000)
                nextPath = passages.remove(rand.nextInt(passages.size()));
            else
                nextPath = passages.remove(rand.nextInt((int)Math.cbrt(passages.size())));
            //for the chosen 'nextPath' check if the cell is not already part of the maze
            //then add it to passages and add it's neighbors too
            depth = nextPath[3];
            row = nextPath[4];
            col = nextPath[5];
            if ( maze.getCell(depth,row,col) == 1 ){
                maze.setCell(nextPath[0],nextPath[1],nextPath[2],0);
                maze.setCell(depth,row,col,0);
                //helper func that evaluate which of the neighbors can be added as path
                passages.addAll(addNeighboringPassages(maze,depth,row,col));
            }
        }
        //secure that there will always be a path to the goal position
        this.createPathForGoal(maze);
        return maze;
    }

    private LinkedList<int[]> addNeighboringPassages(Maze3D maze,int depth ,int row ,int col){
        LinkedList<int[]> passages = new LinkedList<>();

        //checking depth neighbors in distance of 1 - for interesting mazes
        //checking row and column neighbors in distance of 2 - for creating maze with path cells and walls cells
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

        return passages;
    }

    private void createPathForGoal(Maze3D maze){
        int depth = maze.getGoalPosition().getDepthIndex();
        int row = maze.getGoalPosition().getRowIndex();
        int col = maze.getGoalPosition().getColumnIndex();
        //if goal is a wall, make it a path and make sure the path is connected to the main path of the maze
        if(maze.getCell(depth,row,col)==1) {
            maze.setCell(depth,row, col, 0);
            maze.setCell(depth+1,row, col, 0);
            maze.setCell(depth-1,row, col, 0);
            maze.setCell(depth,row+1, col, 0);
            maze.setCell(depth,row-1, col, 0);
            maze.setCell(depth,row, col+1, 0);
            maze.setCell(depth,row, col-1, 0);
        }
    }
}
