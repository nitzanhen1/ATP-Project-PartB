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

    /** this func is used to measure the time of the 'solve' function
     * param: domain - the problem to search on
     * @return the time in millisecond 'solve' operation took
     */
    @Override
    public long measureAlgorithmTimeMillis(ISearchable domain) {
        long t1 = System.currentTimeMillis();
        this.solve(domain);
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }


    /** this func is called inside 'solve' after solve found the GoalState. to retrieve the path
     * @param startState - stop condition, when start is reached the path is complete
     * @param goalState- the first node in the path.
     * @return the full solution path from start to goal
     */
    protected Solution returnPath(AState startState,AState goalState){
        AState currState = goalState;
        Solution solution= new Solution();

        // going thought the parent of the state until the start state is reached.
        while(!(currState.equals(startState))) {
            solution.addSolutionPath(currState);
            currState=currState.getParentState();
        }
        solution.addSolutionPath(currState);
        return solution;
    }
}
