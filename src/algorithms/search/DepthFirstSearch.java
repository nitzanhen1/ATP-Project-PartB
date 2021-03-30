package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

    protected Stack<AState> openList;

    public DepthFirstSearch() {
        super();
        openList = new Stack<AState>();
    }

    public AState pop(){
        evaluated++;
        return openList.pop();
    }

    @Override
    public Solution solve(ISearchable domain) {
        domain.resetSearchable();
        openList.clear();
        evaluated=0;
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        AState currState;
        ArrayList<AState> neighbors;


        openList.push(start);
        while (openList.size()>0){
            currState = this.pop();
            if(!currState.isVisited()) {
                currState.setVisited(true);
                neighbors=domain.getAllSuccessors(currState);
                for (AState s:neighbors){
                    if(!s.isVisited()&&!openList.contains(s)) {
                        openList.push(s);
                        if (s.equals(goal))
                            return returnPath(start, s);
                    }
                }
            }
        }
        return null;

    }

    @Override
    public String getName() {
        return "Depth First Search";
    }
}
