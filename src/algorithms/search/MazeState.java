package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    private Position pos;

    public MazeState(String state, AState parentState, double cost) {
        super(state, parentState, cost);
        pos=new Position(state.charAt(1),state.charAt(3));
    }

    public MazeState(Position pos) {
        super(pos.toString());
    }

}
