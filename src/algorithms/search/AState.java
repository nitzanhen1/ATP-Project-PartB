package algorithms.search;

public abstract class AState {

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
}
