package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int rowSize, int columnSize);
    long measureAlgorithmTimeMillis(int rowSize, int columnSize);
}
