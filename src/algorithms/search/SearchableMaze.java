package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    private Maze maze;
    private MazeState[][] stateMaze;
    private MazeState startState;
    private MazeState goalState;

    public SearchableMaze(Maze maze) {

        this.maze = maze;
        int row = maze.getRowSize();
        int col = maze.getColumnSize();
        stateMaze = new MazeState[row][col];

        startState = new MazeState(maze.getStartPosition());
        int sRow= startState.getRowIndex();
        int sCol=startState.getColumnIndex();
        stateMaze[sRow][sCol] = startState;

        goalState = new MazeState(maze.getGoalPosition());

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

        MazeState mState = new MazeState(state.getState());

        int row = mState.getRowIndex();
        int col = mState.getColumnIndex();

        boolean up = checkSuccessors(row-1,col,state,successors,10);
        boolean left= checkSuccessors(row,col-1,state,successors,10);
        boolean down= checkSuccessors(row+1,col,state,successors,10);
        boolean right = checkSuccessors(row,col+1,state,successors,10);
        if(up || left)
            checkSuccessors(row-1,col-1,state,successors,15);
        if(up || right)
            checkSuccessors(row-1,col+1,state,successors,15);
        if(down || left)
            checkSuccessors(row+1,col-1,state,successors,15);
        if(down || right)
            checkSuccessors(row+1,col+1,state,successors,15);

        return successors;
    }

    public boolean checkSuccessors(int row, int col, AState parentState, ArrayList<AState> successors,double cost){
        if(maze.getCell(row,col)==0) {
            if(stateMaze[row][col]==null)
                stateMaze[row][col]=new MazeState(new Position(row,col),parentState,cost+parentState.getCost());

            successors.add(0,stateMaze[row][col]);
            return true;
        }
        return false;
    }

    public void resetSearchable(){
        for(int i = 0;i<maze.getRowSize(); i++){
            for(int j = 0; j< maze.getColumnSize();j++){
                if (stateMaze[i][j] != null) {
                    if(stateMaze[i][j].equals(startState)){
                        stateMaze[i][j].reset();
                    }
                    else
                        stateMaze[i][j]=null;
                }
            }
        }
    }
}
