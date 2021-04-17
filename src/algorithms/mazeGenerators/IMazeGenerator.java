package algorithms.mazeGenerators;

public interface IMazeGenerator {

    //this func is used to generate mazes
    Maze generate(int rowSize, int columnSize);

    //this func is used to measure time of 'generate' func operation
    long measureAlgorithmTimeMillis(int rowSize, int columnSize);
}
