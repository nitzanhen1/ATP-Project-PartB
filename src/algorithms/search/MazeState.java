package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.Objects;

public class MazeState extends AState {

    private Position pos;

    //constructor with params position, parent and cost
    public MazeState(Position pos, AState parentState, double cost) {
        super(parentState, cost);
        this.pos=pos;
    }

    //constructor with params position
    public MazeState(Position pos) {
        super();
        this.pos=pos;
    }

    public int getRowIndex() {return pos.getRowIndex();}

    public int getColumnIndex() {return pos.getColumnIndex();}

    @Override
    public String toString() {
        return pos.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeState mazeState = (MazeState) o;
        return pos.equals(mazeState.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos);
    }
}
