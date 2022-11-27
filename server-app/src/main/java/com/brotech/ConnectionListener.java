package com.brotech;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener implements Runnable {

    private Server server;
    private ServerSocket socket;
    private boolean isRunning;

    public ConnectionListener(Server server) {
        this.server = server;
        socket = server.getServerSocket();
    }

    public void startListeningConnection(){
        if(isRunning)
            return;
        isRunning = true;
        Thread t= new Thread(this);
        t.start();

    }

    @Override
    public void run() {

        while (isRunning){
            try {
                Socket socket = this.socket.accept();
                Connection connection = new Connection(socket);
                server.addConnection(connection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public synchronized void close() {
        if(!isRunning)
            return;
        try {
            isRunning = false;
            socket.close();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public boolean getIsRunning() {
        return isRunning;
    }
}
