package algorithms.maze3D;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {

    private Maze3D maze;
    private Maze3DState startState;
    private Maze3DState goalState;

    public SearchableMaze3D(Maze3D maze) throws NullPointerException{
        //object adapter of maze to a searching problem maze
        if(maze==null)
            throw new NullPointerException("SearchableMaze3D: expected Maze3D, received null");

        this.maze = maze;

        //save start and goal position3D as 3DStates
        startState = new Maze3DState(maze.getStartPosition());
        goalState = new Maze3DState(maze.getGoalPosition());
    }

    @Override
    public AState getStartState() {
        return startState;
    }

    @Override
    public AState getGoalState() { return goalState;}

    /**
     * @param state a current state that is given
     * @return a list of AStates that represent the neighbors of the current state
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState state) throws NullPointerException{
        if(state==null)
            throw new NullPointerException("getAllSuccessors: expected AState, received null");

        ArrayList<AState> successors= new ArrayList<>();
        Maze3DState mState = (Maze3DState) state;

        int depth = mState.getDepthIndex();
        int row = mState.getRowIndex();
        int col = mState.getColumnIndex();

        //check all 6 direction, if the cell is a path, add to successors
        checkSuccessors(depth+1,row,col,state,successors,10);
        checkSuccessors(depth, row+1,col,state,successors,10);
        checkSuccessors(depth, row,col+1,state,successors,10);
        checkSuccessors(depth-1,row,col,state,successors,10);
        checkSuccessors(depth,row-1,col,state,successors,10);
        checkSuccessors(depth, row,col-1,state,successors,10);

        return successors;
    }

    /**
     * params depth,row,col- the position of the cell in the maze
     * @param parentState - the current state that discovered this state
     * @param successors - the successors state list
     * @param cost - the cost of the path from the parent to this state
     * @return true if added or false if not (used for diagonal successors)
     */
    private boolean checkSuccessors(int depth, int row, int col, AState parentState, ArrayList<AState> successors,double cost){
        if(maze.getCell(depth,row,col)==0) {
            Maze3DState mState = new Maze3DState(new Position3D(depth,row,col),parentState,cost+parentState.getCost());

            successors.add(0,mState);
            return true;
        }
        return false;
    }
}



