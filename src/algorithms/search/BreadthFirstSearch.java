package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected Queue<AState> openList;
    protected HashSet<AState> visited;


    public BreadthFirstSearch() {
        super();
        evaluated=0;
    }

    public AState pop(){
        evaluated++;
        return openList.poll();
    }

    @Override
    public Solution solve(ISearchable domain) {
        openList = new LinkedList<>();
        visited= new HashSet<>();
        evaluated=0;
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();

        openList.add(start);
        visited.add(start);

        AState currState;
        ArrayList<AState> neighbors;

        while (openList.size()>0){
            currState = this.pop();
            if(currState.equals(goal))
                return  returnPath(start,currState);
            neighbors=domain.getAllSuccessors(currState);
            for (AState s:neighbors) {
                if(!visited.contains(s)){
                    openList.add(s);
                    visited.add(s);


                }
            }
        }
        return null;
    }



    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
