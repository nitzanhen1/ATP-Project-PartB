package algorithms.mazeGenerators;

public abstract class AmazeGenerator implements IMazeGenerator {

    @Override
    public long measureAlgorithmTimeMillis(int row, int column) {
        long t1 = System.currentTimeMillis();
        this.generate(row,column);
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }
}
