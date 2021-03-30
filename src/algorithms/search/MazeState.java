package algorithms.search;

public class MazeState extends AState {

    public MazeState(String state, AState parentState, double cost) {
        super(state, parentState, cost);
    }

    public MazeState(String state) {
        super(state);
    }

}
