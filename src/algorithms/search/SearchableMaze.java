package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    private Maze maze;
    private MazeState startState;
    private MazeState goalState;

    public SearchableMaze(Maze maze) throws NullPointerException{
        //object adapter of maze to a searching problem maze
        if(maze==null)
            throw new NullPointerException("SearchableMaze expected Maze, received null");
        this.maze = maze;

        //save start and goal position as states
        startState = new MazeState(maze.getStartPosition());
        goalState = new MazeState(maze.getGoalPosition());

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
        MazeState mState = (MazeState)state;

        int row = mState.getRowIndex();
        int col = mState.getColumnIndex();

        //check all 8 direction, if the cell is a path, add to successors
        //sides cost is 10, diagonal cost is 15
        //boolean params are used as flags for validation of diagonal step

        //clockWise sides
        boolean up = checkSuccessors(row-1,col,state,successors,10);
        boolean right = checkSuccessors(row,col+1,state,successors,10);
        boolean down= checkSuccessors(row+1,col,state,successors,10);
        boolean left= checkSuccessors(row,col-1,state,successors,10);

        //clockWise diagonal
        if(up || right)
            checkSuccessors(row-1,col+1,state,successors,15);
        if(down || right)
            checkSuccessors(row+1,col+1,state,successors,15);
        if(down || left)
            checkSuccessors(row+1,col-1,state,successors,15);
        if(up || left)
            checkSuccessors(row-1,col-1,state,successors,15);
        return successors;
    }

    /**
     * params row,col- the position of the cell in the maze
     * @param parentState - the current state that discovered this state
     * @param successors - the successors state list
     * @param cost - the cost of the path from the parent to this state
     * @return true if added or false if not (used for diagonal successors)
     */
    private boolean checkSuccessors(int row, int col, AState parentState, ArrayList<AState> successors,double cost){
        if(maze.getCell(row,col)==0) {
            MazeState mState = new MazeState(new Position(row,col),parentState,cost+parentState.getCost());
            successors.add(0,mState);
            return true;
        }
        return false;
    }
}
