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
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);

            try {
                String tempDirectoryPath = System.getProperty("java.io.tmpdir");
                //String tempDirectoryPath = "/Users/malkahanimov/Desktop";
                System.out.println(tempDirectoryPath);
                Maze maze = (Maze)(fromClient.readObject());

                Solution solution=null;
                int hashMaze = maze.hashCode();
                String solPath= tempDirectoryPath +"/"+"Solution"+hashMaze;
                //if(Files.exists(Path.of(SolPath))
                File dir = new File(tempDirectoryPath);
                // list the files using a anonymous FileFilter
                File[] files = dir.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.getName().startsWith("Solution"+hashMaze);
                    }
                });
                System.out.println(hashMaze);
                boolean flag = false;
                if(files.length!=0) {
                    //file exist, need to compare files with byteArray
                    System.out.println("len > 0!!!!");
                    ObjectInputStream inSolution;
                    ArrayList<Object> loadSol;
                    for (File file : files) {
                        inSolution = new ObjectInputStream(new FileInputStream(file));
                        loadSol = (ArrayList<Object>) (inSolution.readObject());
                        System.out.println(file.getName());
                        byte[] loadMaze = (byte[]) loadSol.get(0);
                        String s1 = Convert(maze.toByteArray());
                        String s2 = Convert(loadMaze);
                        if (s1.equals(s2)) {
                            System.out.println("found!!!!");
                            solution = (Solution) loadSol.get(1);
                            flag = true;
                            inSolution.close();
                            break;
                        }
                        inSolution.close();
                    }

                }
                if(files.length==0 || !flag){
                    System.out.println("not found!!!!"+flag+" "+files.length);
                    ISearchingAlgorithm searcher = Configurations.getMazeSearchingAlgorithm();
                    ISearchable searchableMaze = new SearchableMaze(maze);
                    solution = searcher.solve(searchableMaze); //solving the maze
                    solPath = solPath + "_" + counter.getAndIncrement(); //Name OF the file
                    ObjectOutputStream outSolution = new ObjectOutputStream(new FileOutputStream(solPath));
                    ArrayList<Object> saveObj = new ArrayList<>();
                    saveObj.add(maze.toByteArray());
                    saveObj.add(solution);
                    outSolution.writeObject(saveObj);
                    outSolution.flush();
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
    public String Convert(byte[] byteMaze)
    {
        String temp="";

        for(int i=0;i<byteMaze.length;i++)
        {
            temp+=String.valueOf(byteMaze[i]);
        }
        return temp;
    }
}
