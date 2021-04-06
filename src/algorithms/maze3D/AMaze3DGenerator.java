package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator{

    @Override
    public long measureAlgorithmTimeMillis(int depthSize,int rowSize, int columnSize) {
        long t1 = System.currentTimeMillis();
        this.generate(depthSize,rowSize,columnSize);
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }

    @Override
    public long measure2(int depthSize, int rowSize, int columnSize){
        long t1 = System.currentTimeMillis();
        this.generate2(depthSize,rowSize,columnSize);
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }

}
