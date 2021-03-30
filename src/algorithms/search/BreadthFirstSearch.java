package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public BreadthFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable domain) {
        Solution solution= new Solution();
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        openList.add(start);
        start.setVisited(true);

        AState currState;
        ArrayList<AState> neighbors;

        while (openList.size()>0){
            currState = this.pop();
            neighbors=domain.getAllPossibleStates(currState);
            for (AState s:neighbors) {
                if(!s.isVisited()){
                    openList.add(s);
                    s.setVisited(true);

                    if(s.equals(goal))
                    {
                        currState = s;

                        while(!(currState.equals(start))) {
                            solution.addSolutionPath(currState);
                            currState=currState.getParentState();
                        }
                        solution.addSolutionPath(currState);
                        return solution;
                    }
                }
            }
        }
        return solution;
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }
}
