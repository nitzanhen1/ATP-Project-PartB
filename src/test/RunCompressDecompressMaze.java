package test;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import IO.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(5, 5); //Generate new maze
        try {
            // save maze to a file
            OutputStream out = new SimpleCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try {
            //read maze from file
            InputStream in = new SimpleDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(maze.toString());
        byte[] mazeByte= maze.toByteArray();
        for(int i=0;i<mazeByte.length;i++)
        {
            System.out.print(Byte.toUnsignedInt(mazeByte[i])+" ");
        }
        System.out.println();

        for(int i=0;i<savedMazeBytes.length;i++)
        {
            System.out.print(Byte.toUnsignedInt(savedMazeBytes[i])+" ");
        }
        System.out.println();

        byte[]load= loadedMaze.toByteArray();
        for(int i=0;i<load.length;i++)
        {
            System.out.print(Byte.toUnsignedInt(load[i])+" ");
        }
        System.out.println();

        System.out.println(String.format("Mazes equal: %s",areMazesEquals));
//maze should be equal to loadedMaze
    }
}
