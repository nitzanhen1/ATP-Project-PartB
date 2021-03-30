package algorithms.maze3D;

public interface IMaze3DGenerator {

    Maze3D generate(int depthSize, int rowSize, int colSize);
    long measureAlgorithmTimeMillis(int depthSize, int rowSize, int columnSize);
}
