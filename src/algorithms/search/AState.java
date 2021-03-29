package algorithms.search;

import java.util.Objects;

public abstract class AState implements Comparable<AState> {

    private String state;
    private AState parentState;
    private double cost;
    boolean visited;


    public AState(String state, AState parentState, double cost) {
        this.state = state;
        this.parentState = parentState;
        this.cost = cost;
        visited=false;
    }

    public AState(String state) {
        this.state = state;
        this.parentState = null;
        this.cost = 0;
        visited=false;

    }
    public String getState() {
        return state;
    }

    public AState getParentState() {
        return parentState;
    }

    public double getCost() {
        return cost;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AState aState = (AState) o;
        if (state != null)
            return state.equals(aState.state);
        return aState.state==null;
    }

    @Override
    public int hashCode() {
        return state != null ? state.hashCode() : 0;
    }

    @Override
    public int compareTo(AState other) {
        return (int)(this.cost - other.cost);
    }
}
