package algorithms.search;

import java.util.ArrayList;

public class Solution {
    ArrayList<AState> solutionPath;

    public Solution() {
        this.solutionPath = new ArrayList<AState>();
    }

    public ArrayList<AState> getSolutionPath() {
        return solutionPath;
    }

    public void addSolutionPath(AState state) {
        this.solutionPath.add(0,state);
    }
}
