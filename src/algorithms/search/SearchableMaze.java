package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    private Maze sMaze;
    private AState[][] stateMaze;
    private AState startState;
    private AState goalState;

    public SearchableMaze(Maze maze) {

        sMaze = maze;
        int row = maze.getRowSize();
        int col = maze.getColumnSize();
        stateMaze = new AState[row][col];
        int sRow=maze.getStartPosition().getRowIndex();
        int sCol=maze.getStartPosition().getColumnIndex();
        stateMaze[sRow][sCol] = new MazeState(maze.getStartPosition().toString());
        startState = stateMaze[sRow][sCol];

        goalState = new MazeState(sMaze.getGoalPosition().toString());

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
    public ArrayList<AState> getAllPossibleStates(AState state) {
        String currPos = state.getState();
        ArrayList<AState> successors= new ArrayList<AState>();

        int i = currPos.indexOf(",");
        int row = Integer.valueOf(currPos.substring(1,i));
        int col = Integer.valueOf(currPos.substring(i+1,currPos.length()-1));

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
            if(stateMaze[row][col]==null)
                stateMaze[row][col]=new MazeState(pos.toString(),state,cost);

            successors.add(0,stateMaze[row][col]);
            return true;
        }
        return false;
    }
}
