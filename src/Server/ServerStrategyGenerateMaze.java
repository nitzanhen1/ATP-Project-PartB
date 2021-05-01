package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            try {
                int [] arraySize =(int [])(fromClient.readObject());

                AMazeGenerator mg= new MyMazeGenerator();
                // generating the new maze and change it to byte array
                Maze maze=mg.generate(arraySize[0],arraySize[1]);
                // creating an instance of MyCompress
                ByteArrayOutputStream byteOut= new ByteArrayOutputStream();
                OutputStream MyCompress = new MyCompressorOutputStream(byteOut);
                // write in the output stream the compressed maze which will be send to the client
                MyCompress.write(maze.toByteArray());
                toClient.writeObject(byteOut.toByteArray());
                toClient.flush();

                byteOut.close();
                MyCompress.close();
                toClient.close();
                fromClient.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
