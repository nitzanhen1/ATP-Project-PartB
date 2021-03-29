package algorithms.search;

import java.util.PriorityQueue;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected PriorityQueue<AState> openList;
    protected int evaluated;

    public ASearchingAlgorithm() {
        openList = new PriorityQueue<AState>();
        evaluated=0;
    }
    public AState pop(){
        evaluated++;
        return openList.poll();
    }

    @Override
    public Solution solve(ISearchable domain) {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return evaluated;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public long measureAlgorithmTimeMillis(ISearchable domain) {
        long t1 = System.currentTimeMillis();
        this.solve(domain);
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }
}
