package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    private Map<String, String> SolMap = new HashMap<String, String>();
    private AtomicInteger counter=new AtomicInteger(1);

    @Override
    public void ServerStrategy(InputStream inputStream, OutputStream outputStream) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);

            try {
                String tempDirectoryPath = System.getProperty("java.io.tmpdir");

                // readObject - read the output from client(the input to server)
                Maze maze = (Maze)(fromClient.readObject());
                ByteArrayOutputStream byteOut= new ByteArrayOutputStream();
                MyCompressorOutputStream MyCompress = new MyCompressorOutputStream(byteOut);
                // write in the output stream the compressed maze which will be send to the client
                MyCompress.write(maze.toByteArray());
                String MazeIntoString = ConvertByteArrayToString(byteOut.toByteArray());
                Solution solution;
                String SolPath;
                /*configuration*/
                ISearchingAlgorithm searcher=Configurations.getMazeSearchingAlgorithm();
                ISearchable searchableMaze = new SearchableMaze(maze);

                if(SolMap.containsKey(MazeIntoString)) //There is already solution for the maze
                {
                    SolPath=SolMap.get(MazeIntoString);
                    ObjectInputStream inSolution = new ObjectInputStream(new FileInputStream(SolPath));
                    solution = (Solution)(inSolution.readObject());
                    inSolution.close();

                }
                else //creating new solution
                {
                    solution=searcher.solve(searchableMaze); //solving the maze
                    SolPath=tempDirectoryPath +"\\"+"Solution"+counter.getAndIncrement(); //Name OF the file
                    ObjectOutputStream outSolution = new ObjectOutputStream(new FileOutputStream(SolPath));
                    outSolution.writeObject(solution);
                    outSolution.flush();
                    addToMap(MazeIntoString,SolPath); //write into the hashMap
                    outSolution.close();

                }

                toClient.writeObject(solution);
                toClient.flush();

                fromClient.close();
                toClient.close();

            }
            catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ConvertByteArrayToString(byte[] byteMaze)
    {
        String temp="";

        for(int i=0;i<byteMaze.length;i++)
        {
            temp+=String.valueOf(byteMaze[i]);
        }
        return temp;
    }

    public synchronized void addToMap(String maze,String path)
    {
        SolMap.put(maze,path);
    }
}
