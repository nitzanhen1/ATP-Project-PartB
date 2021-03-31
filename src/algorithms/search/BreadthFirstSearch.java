package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
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

    public Solution solve(ISearchable domain) {
        domain.resetSearchable();
        openList.clear();
        evaluated=0;
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();

        HashSet<AState> visited = new HashSet<>();
        openList.add(start);
        visited.add(start);

        AState currState;
        ArrayList<AState> neighbors;

        while (openList.size()>0){
            currState = this.pop();
            neighbors=domain.getAllSuccessors(currState);
            for (AState s:neighbors) {
                if(!visited.contains(s)){
                    openList.add(s);
                    visited.add(s);

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
