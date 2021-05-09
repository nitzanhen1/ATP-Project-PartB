package test;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.util.ArrayList;

public class RunSearchOnMaze {
    /*public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(10, 10);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }*/
    public static void main(String[] args) {
        //int[][] array =new int[][][[0,0,0,0],[0,0,0,0]];
IMazeGenerator mg = new MyMazeGenerator();
        Maze maze1  = new Maze(5,5);
        Maze maze2  = mg.generate(5,5);
        Maze maze3  = mg.generate(5,5);
        Maze maze4  = mg.generate(5,5);
        Maze maze5  = mg.generate(5,5);
        Maze maze6  = mg.generate(5,5);
        System.out.println(maze1.toString());
        System.out.println(maze2.toString());
        System.out.println(maze3.toString());
        System.out.println(maze4.toString());
        System.out.println(maze5.toString());
        System.out.println(maze6.toString());

        System.out.println(maze1.hashCode());
        System.out.println(maze2.hashCode());
        System.out.println(maze3.hashCode());
        System.out.println(maze4.hashCode());
        System.out.println(maze5.hashCode());
        System.out.println(maze6.hashCode());
    }
}
