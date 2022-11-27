package com.brotech;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Client implements Runnable{

    private final Connection connection;
    private final List<Client> clientList;

    private boolean isAlive;

    public Client(Connection connection, List<Client> clientList) {
        this.connection = connection;
        this.clientList = clientList;
        isAlive = false;
    }


    @Override
    public void run() {
        while (connection.isAlive()){
            String message = connection.read();
            if(message !=null) {
                System.out.println(clientList.size());
                for (Client c : clientList) {
                    System.out.println(message);
                    c.send(message);
                }
            }
        }
    }

    public void startListening() {
        if(isAlive)
            return;
        isAlive = true;
        Thread t = new Thread(this);
        t.start();
    }

    public synchronized void closeSession(){

        if(!isAlive)
            return;
        isAlive = false;
        connection.close();
    }

    public void send(String message){
        connection.send(message);
        connection.flush();
    }
}
