package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {

    private Maze3D maze;
    private Maze3DState[][][] stateMaze;
    private Maze3DState startState;
    private Maze3DState goalState;

    public SearchableMaze3D(Maze3D maze) {

        this.maze = maze;
        int depth = maze.getDepthSize();
        int row = maze.getRowSize();
        int col = maze.getColumnSize();
        stateMaze = new Maze3DState[depth][row][col];

        startState = new Maze3DState(maze.getStartPosition());
        int sDepth= startState.getDepthIndex();
        int sRow= startState.getRowIndex();
        int sCol=startState.getColumnIndex();
        stateMaze[sDepth][sRow][sCol] = startState;

        goalState = new Maze3DState(maze.getGoalPosition());

    }

    @Override
    public AState getStartState() {
        return startState;
    }

    @Override
    public AState getGoalState() {
        return goalState;

    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState state) {
        ArrayList<AState> successors= new ArrayList<AState>();

        Maze3DState mState = new Maze3DState(state.getState());

        int depth = mState.getDepthIndex();
        int row = mState.getRowIndex();
        int col = mState.getColumnIndex();

        checkSuccessors(depth-1,row,col,state,successors,10);
        checkSuccessors(depth,row-1,col,state,successors,10);
        checkSuccessors(depth, row,col-1,state,successors,10);
        checkSuccessors(depth+1,row,col,state,successors,10);
        checkSuccessors(depth, row+1,col,state,successors,10);
        checkSuccessors(depth, row,col+1,state,successors,10);


        return successors;
    }

    public boolean checkSuccessors(int depth, int row, int col, AState parentState, ArrayList<AState> successors,double cost){
        if(maze.getCell(depth,row,col)==0) {
            if(stateMaze[depth][row][col]==null)
                stateMaze[depth][row][col]=new Maze3DState(new Position3D(depth,row,col),parentState,cost+parentState.getCost());

            successors.add(0,stateMaze[depth][row][col]);
            return true;
        }
        return false;
    }

    public void resetSearchable(){
        for(int i = 0;i<maze.getDepthSize(); i++){
            for(int j = 0; j< maze.getRowSize();j++){
                for(int k = 0;k < maze.getColumnSize();k++) {
                    if (stateMaze[i][j][k] != null) {
                        if (stateMaze[i][j][k].equals(startState)) {
                            stateMaze[i][j][k].reset();
                        } else
                            stateMaze[i][j][k] = null;
                    }
                }
            }
        }
    }


}
