package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void ServerStrategy(InputStream inputStream, OutputStream outputStream) {
        try {
            //initialize the streams input from the client and output to the client

            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);

            try {
                //receive from client sizes of wanted maze
                // then transform the maze to byte array representation
                // and send it to the compressor
                //client receive compressed array
                int [] arraySize =(int [])(fromClient.readObject());
                /*configuration*/
                AMazeGenerator mg= Configurations.getMazeGeneratingAlgorithm();
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
