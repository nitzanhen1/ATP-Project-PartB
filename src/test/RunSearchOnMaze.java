package test;
        import algorithms.mazeGenerators.*;
        import algorithms.search.*;
        import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {


        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(6, 6);
        System.out.println(maze.toString());

        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
        solveProblem(searchableMaze, new BreadthFirstSearch());



//        SearchableTest st = new SearchableTest();
//        solveProblem(st, new BestFirstSearch());
//        solveProblem(st, new BreadthFirstSearch());
//        solveProblem(st, new DepthFirstSearch());
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
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }*/
    }
}