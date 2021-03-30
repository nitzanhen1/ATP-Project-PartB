package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected Queue<AState> openList;


    public BreadthFirstSearch() {
        super();
        openList = new LinkedList<AState>();
        evaluated=0;
    }

    public AState pop(){
        evaluated++;
        return openList.poll();
    }

    @Override
    public Solution solve(ISearchable domain) {
        domain.resetSearchable();
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        openList.add(start);
        start.setVisited(true);

        AState currState;
        ArrayList<AState> neighbors;

        while (openList.size()>0){
            currState = this.pop();
            neighbors=domain.getAllSuccessors(currState);
            for (AState s:neighbors) {
                if(!s.isVisited()){
                    openList.add(s);
                    s.setVisited(true);

                    if(s.equals(goal))
                        return  returnPath(start,s);

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
