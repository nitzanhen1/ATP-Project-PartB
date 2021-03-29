package algorithms.search;
import algorithms.mazeGenerators.Maze;
import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    private Maze sMaze;

    public SearchableMaze(Maze maze) {
        sMaze = maze;
    }

    @Override
    public AState getStartState() {
        return null;
    }

    @Override
    public AState getGoalState() {
        return null;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        return null;
    }
}
