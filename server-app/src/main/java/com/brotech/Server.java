package com.brotech;



import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server{
    private ServerSocket serverSocket;
    private int portNumber;
    private BufferedReader bufferedReader;
    private ConnectionListener connectionListener;

    private List<Client> clientList;

    public Server(int portNumber) throws IOException {
        this.portNumber = portNumber;
        serverSocket = new ServerSocket(this.portNumber);
        connectionListener = new ConnectionListener(this);
        clientList = new ArrayList<>();
    }

    public void start() {
        try {

            connectionListener.startListeningConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = bufferedReader.readLine()) !=null && connectionListener.getIsRunning()){
                if(message.equals("exit")){
                    System.out.println("server is going to stop working:.... " +message);
                    break;
                }
                System.out.println("Admin :" + message);
                for (Client c : clientList) {
                    c.send(message);
                }

            }
            stopSever();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private void stopSever() throws IOException {
        connectionListener.close();
        for (Client c:clientList) {
            c.closeSession();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public synchronized void addConnection(Connection connection) {
        Client client = new Client(connection,clientList);
        clientList.add(client);
        client.startListening();
    }
}
