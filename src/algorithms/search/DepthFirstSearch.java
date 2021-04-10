package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm{

    protected Stack<AState> openList;
    protected HashSet<AState> visited;

    public DepthFirstSearch() {
        super();

    }
    public AState pop(){
        evaluated++;
        return openList.pop();
    }

    public Solution solve(ISearchable domain) {
        openList= new Stack<>();
        evaluated=0;
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        AState currState;
        ArrayList<AState> neighbors;
        HashSet<AState> visited= new HashSet<>();

        openList.push(start);

        while (openList.size()>0){
            currState = this.pop();

            if (currState.equals(goal))
                return returnPath(start, currState);

            if(!visited.contains(currState)) {
                visited.add(currState);
                neighbors=domain.getAllSuccessors(currState);
                for (AState s:neighbors){
                    if(!visited.contains(s)) {
                        openList.push(s);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}
