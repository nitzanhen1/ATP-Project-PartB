package algorithms.search;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    //protected Queue<AState> openList;
    protected int evaluated;

    public ASearchingAlgorithm() {
        //openList = new LinkedList<AState>();
        evaluated=0;
    }

    //public abstract AState pop();

    @Override
    public int getNumberOfNodesEvaluated() {
        return evaluated;
    }

    @Override
    public long measureAlgorithmTimeMillis(ISearchable domain) {
        long t1 = System.currentTimeMillis();
        this.solve(domain);
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }

    public Solution returnPath(AState startState,AState goalState){
        AState currState = goalState;
        Solution solution= new Solution();

        while(!(currState.equals(startState))) {
            solution.addSolutionPath(currState);
            currState=currState.getParentState();
        }
        solution.addSolutionPath(currState);
        return solution;
    }
}
