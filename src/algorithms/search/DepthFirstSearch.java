package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

    //open list holds states discovered via neighbors but not yet evaluated
    protected Stack<AState> openList;
    //visited hold all the states the algorithm evaluated (like closed list). using hashSet for search in O(1)
    protected HashSet<AState> visited;

    public DepthFirstSearch() { super(); }

    //removes the top of the openList stack and increment evaluated
    public AState pop() throws IndexOutOfBoundsException{
        if(openList.isEmpty())
            throw new IndexOutOfBoundsException("openList is empty, can't remove element");
        evaluated++;
        return openList.pop();
    }

    //iterative DFS
    public Solution solve(ISearchable domain) throws NullPointerException{
        if(domain == null)
            throw new NullPointerException("expected ISearchable, received null");
        openList= new Stack<>();
        evaluated=0;
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        AState currState;
        ArrayList<AState> neighbors;
        visited= new HashSet<>();

        openList.push(start);

        //for each state that was removed from open we check if it is goal
        //else if it's not in visited get its neighbors and if they are not in visited too, insert to open list
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
        throw new NullPointerException("domain is unsolvable");
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}
