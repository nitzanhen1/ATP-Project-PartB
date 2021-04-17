package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    //map holds states hashcode as key, ans the state as value
    //is used for search in O(1) and for comparing different paths to the same state
    HashMap<Integer,AState> map;

    public BestFirstSearch() {
        super();
    }

    public Solution solve(ISearchable domain) throws NullPointerException{
        openList = new PriorityQueue<>();
        map = new HashMap<>();
        this.initSolve(domain);

        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        AState currState;
        ArrayList<AState> neighbors;

        map.put(start.hashCode(),start);
        int sHashCode;

        //for each state that was removed from open we check if it is goal
        //else add to visited and get its neighbors
        while (openList.size()>0){
            currState = this.pop();
            map.remove(currState.hashCode());

            if(currState.equals(goal))
                return  returnPath(start,currState);

            visited.add(currState);
            neighbors=domain.getAllSuccessors(currState);
            for (AState s:neighbors) {
                sHashCode = s.hashCode();
                //if state hasn't been reached at all add to open and map
                if(!map.containsKey(sHashCode)){
                    if(!visited.contains(s)){
                        openList.add(s);
                        map.put(sHashCode,s);
                    }
                }
                //if state is already inside map, compare the paths and take the cheaper one
                else if(s.compareTo(map.get(sHashCode))< 0){
                    openList.remove(map.get(sHashCode));
                    openList.add(s);
                    map.replace(sHashCode,s);
                }
            }
        }
        throw new NullPointerException("domain is unsolvable");
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
