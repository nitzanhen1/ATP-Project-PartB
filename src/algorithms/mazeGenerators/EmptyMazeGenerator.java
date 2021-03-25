package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AmazeGenerator {

    @Override
    public Maze generate(int row, int column) {

        return new Maze(row, column);
    }
}
