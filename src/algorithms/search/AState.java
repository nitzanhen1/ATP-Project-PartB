package algorithms.search;

public abstract class AState implements Comparable<AState> {

    //private String state;
    private AState parentState;
    private double cost;

    // create state
    public AState(/*String state,*/ AState parentState, double cost) {
        //this.state = state;
        this.parentState = parentState;
        this.cost = cost;
    }

    // create start state
    public AState(/*String state*/) {
        //this.state = state;
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
