package algorithms.search;

public abstract class AState {

    private String state;
    private AState parentState;
    private double cost;

    public String getState() {
        return state;
    }

    public AState getParentState() {
        return parentState;
    }

    public double getCost() {
        return cost;
    }
}
