package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public BreadthFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable domain) {
        Solution solution= new Solution();;
        openList.add(domain.getStartState());
        domain.getStartState().setVisited(true);

        while (openList.size()>0){
            AState currState = this.pop();
            ArrayList<AState> neighbors=domain.getAllPossibleStates(currState);
            for (AState s:neighbors) {
                if(!s.isVisited()){
                    openList.add(s);
                    s.setVisited(true);
                    if(s.equals(domain.getGoalState()))
                    {
                        currState = s;
                        while(!(currState.equals(domain.getStartState()))) {
                            System.out.println(currState.toString());
                            solution.addSolutionPath(currState);
                            currState=currState.getParentState();
                        }
                        solution.addSolutionPath(currState);
                        return solution;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }
}
