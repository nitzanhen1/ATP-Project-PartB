package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int evaluated;

    public ASearchingAlgorithm() {
        evaluated=0;
    }

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
