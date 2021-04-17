package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator{

    /** this func is used to measure the time of the 'generate' function
     * params: depthSize, rowSize, columnSize determine the size of the maze
     * @return the time in millisecond 'generate' operation took
     */
    @Override
    public long measureAlgorithmTimeMillis(int depthSize,int rowSize, int columnSize) {
        long t1 = System.currentTimeMillis();
        this.generate(depthSize,rowSize,columnSize);
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }

}
