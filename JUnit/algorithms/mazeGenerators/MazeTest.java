package algorithms.mazeGenerators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    @Test
    void toByteArray() {
        MyMazeGenerator mg = new MyMazeGenerator();
        int rowSize=4;
        int colSize=130;
        Maze maze = mg.generate(rowSize,colSize);
        System.out.println(maze.toString());
        byte[] byteMaze = maze.toByteArray();

        for(int i=0;i<byteMaze.length;i++)
        {
        //    System.out.print(Byte.toUnsignedInt(byteMaze[i])+" ");
        }
        System.out.println();

        Maze maze2 = new Maze(byteMaze);

        System.out.println(maze2.toString());

        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                assertEquals(maze.getMaze()[i][j],maze2.getMaze()[i][j] );
            }
        }
        assertTrue(maze.getStartPosition().equals(maze2.getStartPosition()));
        assertTrue(maze.getGoalPosition().equals(maze2.getGoalPosition()));



    }
}