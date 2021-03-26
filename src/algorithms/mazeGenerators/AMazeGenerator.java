package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public long measureAlgorithmTimeMillis(int rowSize, int columnSize) {
        long t1 = System.currentTimeMillis();
        this.generate(rowSize,columnSize);
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }
}
