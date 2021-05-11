package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    private Map<String, String> SolMap = new HashMap<String, String>();
    private AtomicInteger counter=new AtomicInteger(1);

    @Override
    public void ServerStrategy(InputStream inputStream, OutputStream outputStream) {
        try {
            //initialize the streams input from the client and output to the client

            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);

            try {
                //receive from client maze and search if it has been solved before
                Maze maze = (Maze)(fromClient.readObject());
                //to identify the maze, we get its hashcode and search in the temp dir if there is a solution for this hash code
                //each solution is save in the temp dir as: solution*hashcode*_uniqueNum
                int hashMaze = maze.hashCode();
                String tempDirectoryPath = System.getProperty("java.io.tmpdir");
                String solPath= tempDirectoryPath +"\\"+"Solution"+hashMaze;

                //search for the files with the hashcode of the maze
                File dir = new File(tempDirectoryPath);
                File[] files = dir.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.getName().startsWith("Solution"+hashMaze);
                    }
                });
                boolean flag = false;
                Solution solution=null;
                if(files.length!=0) {
                    //files with this hashcode exist,then compare with byteArray of the maze that is save inside the files
                    ObjectInputStream inSolution;
                    ArrayList<Object> loadSol;
                    for (File file : files) {
                        inSolution = new ObjectInputStream(new FileInputStream(file));
                        loadSol = (ArrayList<Object>) (inSolution.readObject());
                        byte[] loadMaze = (byte[]) loadSol.get(0);
                        String s1 = Convert(maze.toByteArray());
                        String s2 = Convert(loadMaze);
                        //if the byte array string of a maze is equal then get the solution saved inside the file
                        if (s1.equals(s2)) {
                            solution = (Solution) loadSol.get(1);
                            flag = true;
                            inSolution.close();
                            break;
                        }
                        inSolution.close();
                    }
                }
                //if there are no files with the hashcode name or non of these files contains the correct maze, search for new solution
                if(files.length==0 || !flag){
                    //create searcher and solvve the maze
                    ISearchingAlgorithm searcher = Configurations.getMazeSearchingAlgorithm();
                    ISearchable searchableMaze = new SearchableMaze(maze);
                    solution = searcher.solve(searchableMaze);
                    //creating unique file name
                    solPath = solPath + "_" + counter.getAndIncrement(); //Name OF the file
                    //creating output stream for the file
                    ObjectOutputStream outSolution = new ObjectOutputStream(new FileOutputStream(solPath));
                    //array saveObj will contain first the byteArray of maze, then the solution
                    ArrayList<Object> saveObj = new ArrayList<>();
                    saveObj.add(maze.toByteArray());
                    saveObj.add(solution);
                    //writing to the file
                    outSolution.writeObject(saveObj);
                    outSolution.flush();
                    outSolution.close();
                }
                //writing solution to the client
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
    //converting bytearray to string
    private String Convert(byte[] byteMaze)
    {
        String temp="";

        for(int i=0;i<byteMaze.length;i++)
        {
            temp+=String.valueOf(byteMaze[i]);
        }
        return temp;
    }
}
