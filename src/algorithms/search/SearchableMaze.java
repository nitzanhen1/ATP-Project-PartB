package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    private Maze sMaze;

    public SearchableMaze(Maze maze) {

        sMaze = maze;
    }

    @Override
    public AState getStartState() {
        AState start = new MazeState(sMaze.getStartPosition());
        return start;
    }

    @Override
    public AState getGoalState() {
        AState goal = new MazeState(sMaze.getGoalPosition());
        return goal;

    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        String currPos = state.getState();
        ArrayList<AState> successors= new ArrayList<AState>();
        int row = currPos.charAt(1);
        int col = currPos.charAt(3);


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

    public boolean checkSuccessors(int row, int col, AState state, ArrayList<AState> successors,double cost){
        if(sMaze.getCell(row,col)==0) {
            Position pos = new Position(row,col);
            successors.add(new MazeState(pos.toString(),state,cost));
            return true;
        }
        return false;
    }
}
