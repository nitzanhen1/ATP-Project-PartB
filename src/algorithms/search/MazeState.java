package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    private Position pos;

    public MazeState(Position pos, AState parentState, double cost) {
        super(pos.toString(), parentState, cost);
        this.pos=pos;
    }

    public MazeState(Position pos) {
        super(pos.toString());
        this.pos=pos;
    }

    public MazeState(String state){
        super(state);

        int i = state.indexOf(",");
        int row = Integer.valueOf(state.substring(1,i));
        int col = Integer.valueOf(state.substring(i+1,state.length()-1));
        pos  = new Position(row,col);
    }

    public int getRowIndex() {

        return pos.getRowIndex();
    }

    public int getColumnIndex() {

        return pos.getColumnIndex();
    }

}
