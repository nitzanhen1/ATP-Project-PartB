package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

public class Maze3DState extends AState {

    private Position3D pos;

    public Maze3DState(Position3D pos, AState parentState, double cost) {
        super(pos.toString(), parentState, cost);
        this.pos=pos;
    }

    public Maze3DState(Position3D pos) {
        super(pos.toString());
        this.pos=pos;
    }

    public Maze3DState(String state){
        super(state);

        int i = state.indexOf(",");
        int depth = Integer.valueOf(state.substring(1,i));
        String sub = state.substring(i+1,state.length()-1);
        i = sub.indexOf(",");
        int row = Integer.valueOf(sub.substring(0,i));
        int col = Integer.valueOf(sub.substring(i+1,sub.length()));
        pos  = new Position3D(depth,row,col);
    }

    public int getDepthIndex() {

        return pos.getDepthIndex();
    }

    public int getRowIndex() {

        return pos.getRowIndex();
    }

    public int getColumnIndex() {

        return pos.getColumnIndex();
    }
}
