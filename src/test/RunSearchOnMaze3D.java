package test;
import algorithms.maze3D.*;
import algorithms.search.*;
import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(5, 5, 5);
        System.out.println(maze.toString());
        SearchableMaze3D searchableMaze3D = new SearchableMaze3D(maze);
        solveProblem(searchableMaze3D, new BreadthFirstSearch());
        solveProblem(searchableMaze3D, new DepthFirstSearch());
        solveProblem(searchableMaze3D, new BestFirstSearch());

        Maze3D maze2 = mg.generate2(5, 5, 5);
        System.out.println(maze2.toString());
        SearchableMaze3D searchableMaze3D2 = new SearchableMaze3D(maze2);
        solveProblem(searchableMaze3D2, new BreadthFirstSearch());
        solveProblem(searchableMaze3D2, new DepthFirstSearch());
        solveProblem(searchableMaze3D2, new BestFirstSearch());

        //System.out.println(String.format("Start Position: %s", maze.getStartPosition())); // format "{row,column}"
        // prints the maze exit position
        //System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
//Solve a searching problem with a searcher
        System.out.println(String.format("Maze Solving time(ms): %s", searcher.measureAlgorithmTimeMillis(domain)));

        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        System.out.println(solutionPath.toString());
        /*for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
        }*/


    }
}
