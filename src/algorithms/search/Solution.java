package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    ArrayList<AState> solutionPath;

    public Solution() {this.solutionPath = new ArrayList<>(); }

    public ArrayList<AState> getSolutionPath() {
        return solutionPath;
    }

    //add state to the solution list, at index 0
    public void addSolutionPath(AState state) throws NullPointerException{
        if(state==null)
            throw new NullPointerException("expected AState, received Null");
        this.solutionPath.add(0,state);
    }
}
