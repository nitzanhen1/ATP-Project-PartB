package algorithms.maze3D;
import java.util.Objects;

public class Position3D {
    private int depth;
    private int row;
    private int column;

    public Position3D(int depth, int row, int column) {
        this.depth = depth;
        this.row = row;
        this.column = column;
    }

    public int getDepthIndex() {
        return depth;
    }

    public int getRowIndex() {
        return row;
    }

    public int getColumnIndex() {
        return column;
    }

    @Override
    public String toString() {
        return "{" + depth +","+ row +","+ column +"}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position3D that = (Position3D) o;
        return depth == that.depth && row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depth, row, column);
    }
}
