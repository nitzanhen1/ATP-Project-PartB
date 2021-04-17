package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * params rowSize,columnSize- determine the size of the maze
     * @return empty maze (all cells values are 0)
     */
    @Override
    public Maze generate(int rowSize, int columnSize) {

        return new Maze(rowSize, columnSize);
    }
}
