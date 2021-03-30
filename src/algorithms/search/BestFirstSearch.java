package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        super();
        openList = new PriorityQueue<AState>();
    }

    public Solution solve(ISearchable domain) {
        return super.solve(domain);
    }

    @Override
    public String getName() {
        return "Best First Search";
    }
}
