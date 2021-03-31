package algorithms.search;

import java.util.*;

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
        HashSet<AState> visited= new HashSet<>();
        HashSet<AState> openListHelper= new HashSet<>();

        openList.push(start);
        openListHelper.add(start);

        while (openList.size()>0){
            currState = this.pop();
            openListHelper.remove(currState);

            if(!visited.contains(currState)) {
                visited.add(currState);
                neighbors=domain.getAllSuccessors(currState);
                for (AState s:neighbors){
                    if(!visited.contains(s)&&!openListHelper.contains(s)) {
                        openList.push(s);
                        openListHelper.add(s);

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
