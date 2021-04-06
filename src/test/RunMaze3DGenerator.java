package test;

import algorithms.maze3D.*;
import algorithms.mazeGenerators.Position;

public class RunMaze3DGenerator {
    public static void main(String[] args) {

        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMaze3DGenerator mazeGenerator) {
// prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(500/*rows*/,500/*columns*/,500)));

        System.out.println(String.format("Maze generation 2 time(ms): %s", mazeGenerator.measure2(500/*rows*/,500/*columns*/,500)));
// generate another maze
        //Maze3D maze = mazeGenerator.generate(2/*rows*/, 2/*columns*/,2);
// prints the maze
        //maze.print();
        //System.out.println(maze.toString());
// get the maze entrance
        //Position3D startPosition = maze.getStartPosition();
// print the start position
        //System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        //System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}
