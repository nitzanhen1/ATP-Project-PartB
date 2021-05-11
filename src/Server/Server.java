package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool; // Thread pool


    /**
     * @param port get port to create server socket
     * @param listeningIntervalMS interval that determine the time in millisecond that the socket will wait to accept clients
     * @param strategy determine that strategy that the serve will use to serve the clients
     */
    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        /*configuration*/
        Configurations.getInstance();
        this.threadPool = Executors.newFixedThreadPool(Configurations.getThreadPoolSize());
    }

    public void start(){
        //sending threads to wait until they receive clients.
        new Thread(() -> {
            runServer();
        }).start();
    }
    public void runServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            while (!stop) {
                try {
                    //receiving  all clients, and call handle clients according to the thread pool sizes
                    Socket clientSocket = serverSocket.accept();

                    threadPool.submit(() -> {
                        handleClient(clientSocket);
                    });

                } catch (SocketTimeoutException e){
                    //System.out.println("Socket timeout");
                }
            }
            serverSocket.close();
            threadPool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            //for each client call the strategy requested.
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stop(){
        stop = true;
    }
}
