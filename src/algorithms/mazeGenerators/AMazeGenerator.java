package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    /** this func is used to measure the time of the 'generate' function
     * params: rowSize, columnSize determine the size of the maze
     * @return the time in millisecond 'generate' operation took
     */
    @Override
    public long measureAlgorithmTimeMillis(int rowSize, int columnSize) {
        long t1 = System.currentTimeMillis();
        this.generate(rowSize,columnSize);
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }
}
