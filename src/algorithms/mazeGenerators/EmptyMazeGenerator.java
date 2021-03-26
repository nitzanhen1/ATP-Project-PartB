package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rowSize, int columnSize) {

        return new Maze(rowSize, columnSize);
    }
}
