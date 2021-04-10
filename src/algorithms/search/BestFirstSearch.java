package algorithms.search;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        super();
    }

//    public Solution solve(ISearchable domain) {
//        openList = new PriorityQueue<>();
//        visited = new HashSet<>();
//        HashMap<Integer,AState> dict = new HashMap<>();
//
//        evaluated=0;
//        AState start = domain.getStartState();
//        AState goal = domain.getGoalState();
//
//        openList.add(start);
//        visited.add(start);
//        dict.put(start.hashCode(),start);
//
//        AState currState;
//        ArrayList<AState> neighbors;
//
//        while (openList.size()>0){
//            currState = this.pop();
//            if(currState.equals(goal))
//                return  returnPath(start,currState);
//            neighbors=domain.getAllSuccessors(currState);
//            for (AState s:neighbors) {
//                if(!visited.contains(s)){
//                    openList.add(s);
//                    visited.add(s);
//                    dict.put(s.hashCode(),s);
//
//                }
//                else {
//                     if(s.compareTo(dict.get(s.hashCode()))< 0){
//                         visited.remove(dict.get(s.hashCode()));
//                         visited.add(s);
//                         dict.replace(s.hashCode(),s);
//                     }
//                }
//            }
//        }
//        return null;
//    }

    public Solution solve(ISearchable domain) {
        openList = new PriorityQueue<>();
        visited = new HashSet<>();
        HashMap<Integer,AState> map = new HashMap<>();

        evaluated=0;
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();

        openList.add(start);
        visited.add(start);
        map.put(start.hashCode(),start);

        AState currState;
        ArrayList<AState> neighbors;
        int sHashCode;

        while (openList.size()>0){
            currState = this.pop();
            map.remove(currState.hashCode());

            visited.add(currState);
            if(currState.equals(goal))
                return  returnPath(start,currState);

            neighbors=domain.getAllSuccessors(currState);
            for (AState s:neighbors) {
                sHashCode = s.hashCode();
                if(!map.containsKey(sHashCode)){
                    if(!visited.contains(s)){
                        openList.add(s);
                        map.put(sHashCode,s);
                    }
                }

                else if(s.compareTo(map.get(sHashCode))< 0){
                    openList.remove(map.get(sHashCode));
                    openList.add(s);
                    map.replace(sHashCode,s);
                }
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
