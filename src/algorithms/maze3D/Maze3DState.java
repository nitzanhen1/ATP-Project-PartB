package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

import java.util.Objects;

public class Maze3DState extends AState {

    private Position3D pos;

    public Maze3DState(Position3D pos, AState parentState, double cost) {
        super(parentState, cost);
        this.pos=pos;
    }

    public Maze3DState(Position3D pos) {
        super();
        this.pos=pos;
    }

    public int getDepthIndex() {

        return pos.getDepthIndex();
    }

    public int getRowIndex() {

        return pos.getRowIndex();
    }

    public int getColumnIndex() {

        return pos.getColumnIndex();
    }

    @Override
    public String toString() {
        return pos.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze3DState that = (Maze3DState) o;
        return pos.equals(that.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos);
    }
}
