package algorithms.search;

import java.io.Serializable;

public abstract class AState implements Comparable<AState> , Serializable {

    private AState parentState;
    private double cost;

    //constructor with params, parent and cost
    public AState(AState parentState, double cost) {
        this.parentState = parentState;
        this.cost = cost;
    }

    //constructor without params
    public AState() {
        this.parentState = null;
        this.cost = 0;

    }

    public AState getParentState() {
        return parentState;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o) ;

    @Override
    public abstract int hashCode() ;

    @Override
    public int compareTo(AState other) {
        return (int)(this.cost - other.cost);
    }
}
