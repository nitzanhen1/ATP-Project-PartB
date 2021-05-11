package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client{
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    /**
     * @param serverIP get local IP Host for the socket
     * @param serverPort get a port for the socket
     * @param strategy is the strategy that with client will activate when communicating with the server
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void communicateWithServer() {
        try {
            //create a new socket and activate the strategy
            Socket socket = new Socket(serverIP,serverPort);
            //System.out.println("client connected to server - IP = " + serverIP + ", Port = " + serverPort);
            strategy.clientStrategy(socket.getInputStream(),socket.getOutputStream());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
