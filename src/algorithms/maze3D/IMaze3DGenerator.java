package algorithms.maze3D;

public interface IMaze3DGenerator {

    //this func is used to generate  3D mazes
    Maze3D generate(int depthSize, int rowSize, int colSize);

    //this func is used to measure time of 'generate' func operation
    long measureAlgorithmTimeMillis(int depthSize, int rowSize, int columnSize);
}
