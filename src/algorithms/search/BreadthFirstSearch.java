package algorithms.search;

import java.util.PriorityQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public BreadthFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable domain) {
        Solution solution= new Solution();

        openList.add(domain.getStartState());
        domain.getStartState().setVisited(true);

        while (openList.size()>0){
            AState currState = this.pop();
            for (AState s:domain.getAllPossibleStates(currState)) {
                if(!s.isVisited()){
                    openList.add(s);
                    s.setVisited(true);
                    //if(s.equals(domain.getGoalState()))


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
