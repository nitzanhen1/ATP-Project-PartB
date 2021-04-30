package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client{
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void communicateWithServer() {
        try {
            Socket socket = new Socket(serverIP,serverPort);
            System.out.println("client connected to server - IP = " + serverIP + ", Port = " + serverPort);
            strategy.clientStrategy(socket.getInputStream(),socket.getOutputStream());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
