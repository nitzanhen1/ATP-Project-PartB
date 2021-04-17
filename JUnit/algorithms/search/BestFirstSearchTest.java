package algorithms.search;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    BestFirstSearch bfs = new BestFirstSearch();


    IMazeGenerator emg = new EmptyMazeGenerator();
    Maze eMaze = emg.generate(5, 5);
    SearchableMaze sm = new SearchableMaze(eMaze);


    @Test
    void checkNullArg() {
        boolean thrown=false;
        try {
            bfs.solve(null);
        }
        catch (NullPointerException e){
            thrown=true;
        }
        assertTrue(thrown);
    }

    @Test
    void getEmptyMazeSolutionCost5x5() {
        Solution solution = bfs.solve(sm);
        ArrayList<AState> path= solution.getSolutionPath();
        double totalCost=path.get(path.size()-1).getCost();

        int diffRow =eMaze.getGoalPosition().getRowIndex()-eMaze.getStartPosition().getRowIndex();
        int diffCol = 4-diffRow;
        int pathCost= diffRow*15+diffCol*10;
        assertEquals(pathCost,totalCost);
    }

    @Test
    void checkPathBoundaries(){
        Solution solution = bfs.solve(sm);
        ArrayList<AState> path= solution.getSolutionPath();

        assertAll(()->assertEquals(sm.getStartState(),path.get(0)),
                ()->assertEquals(sm.getGoalState(),path.get(path.size()-1)));
    }

    @Test
    void getName() {
        assertEquals(bfs.getClass().getSimpleName(),bfs.getName());
    }
}