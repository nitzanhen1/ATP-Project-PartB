package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configurations {
    //configuration is a singleton class, and is used to change the properties of thread number, generation and solving algorithms

    private static Configurations conf = null;
    private static Properties properties ;
    private static final String path=System.getProperty("user.dir")+"/resources/config.properties";

    //private constructor, create only one instance, and loading the last properties the user used
    private Configurations(){
        properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this func try and call the constructor if it hasn't been called before
    //else it will return the instance that was created before
    public static Configurations getInstance(){
        if(conf==null)
            conf = new Configurations();
        return conf;
    }

//    public static void loadProperties(){
//        try {
//            properties.load(new FileInputStream(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //getters of the 3 properties:

    public static int getThreadPoolSize(){
        return Integer.parseInt(properties.getProperty("threadPoolSize"));}

    public static AMazeGenerator getMazeGeneratingAlgorithm(){
        String generator=properties.getProperty("mazeGeneratingAlgorithm");
        if(generator.compareTo("EmptyMazeGenerator")==0)
            return (new EmptyMazeGenerator());
        else if(generator.compareTo("SimpleMazeGenerator")==0)
            return (new SimpleMazeGenerator());
        else
            return (new MyMazeGenerator());
    }

    public static ASearchingAlgorithm getMazeSearchingAlgorithm()
    {
        String algorithm=properties.getProperty("mazeSearchingAlgorithm");
        if(algorithm.compareTo("BreadthFirstSearch")==0)
            return (new BreadthFirstSearch());
        else if(algorithm.compareTo("DepthFirstSearch")==0)
            return (new DepthFirstSearch());
        else
            return (new BestFirstSearch());
    }

    //setters of the 3 properties:

    public static void setThreadPoolSize(int numOfTreads){
        properties.setProperty("threadPoolSize",String.valueOf(numOfTreads));
        try {
            properties.store(new FileOutputStream(path),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMazeGeneratingAlgorithm(String mazeGeneratingAlgorithm){
        properties.setProperty("threadPoolSize",mazeGeneratingAlgorithm);
        try {
            properties.store(new FileOutputStream(path),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMazeSearchingAlgorithm(String mazeSearchingAlgorithm){
        properties.setProperty("threadPoolSize",mazeSearchingAlgorithm);
        try {
            properties.store(new FileOutputStream(path),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
