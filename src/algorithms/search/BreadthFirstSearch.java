package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    //open list holds states discovered via neighbors but not yet evaluated
    protected Queue<AState> openList;
    //visited hold all the states the algorithm found. using hashSet for search in O(1)
    protected HashSet<AState> visited;

    public BreadthFirstSearch() {
        super();
    }

    //removes from openList and increment evaluated
    public AState pop() throws IndexOutOfBoundsException{
        if(openList.isEmpty())
            throw new IndexOutOfBoundsException("openList is empty, can't remove element");
        evaluated++;
        return openList.poll();
    }

    //shared code between BFS and bestFirstSearch
    //initialize the solve func by checking not null, creating data structures and adding start state
    protected void initSolve(ISearchable domain) throws NullPointerException{
        if(domain == null)
            throw new NullPointerException("expected ISearchable, received null");

        visited= new HashSet<>();
        evaluated=0;
        AState start = domain.getStartState();

        openList.add(start);
        visited.add(start);
    }

    @Override
    public Solution solve(ISearchable domain) throws NullPointerException{
        openList = new LinkedList<>();
        this.initSolve(domain);

        AState goal = domain.getGoalState();
        AState currState;
        ArrayList<AState> neighbors;

        //for each state that was removed from open we check if it is goal, else get its neighbors and mark them as visited
        while (openList.size()>0){
            currState = this.pop();
            if(currState.equals(goal))
                return  returnPath(domain.getStartState(),currState);
            neighbors=domain.getAllSuccessors(currState);
            for (AState s:neighbors) {
                if(!visited.contains(s)){
                    openList.add(s);
                    visited.add(s);
                }
            }
        }
        throw new NullPointerException("domain is unsolvable");
    }


    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
