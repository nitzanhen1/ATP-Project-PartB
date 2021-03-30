package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState {
    public Maze3DState(String state, AState parentState, double cost) {
        super(state, parentState, cost);
    }

    public Maze3DState(String state) {
        super(state);
    }
}
