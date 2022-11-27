package com.brotech;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection{

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean isAlive;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        isAlive = true;
    }

    public void startSession(){
        if(isAlive)
            return;
        isAlive = true;
    }


    public String read(){
        try {
            if( reader.ready() ){
                return reader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public void send(String message) {
        try {
            writer.write(message);
        }catch (IOException exception){
            System.out.println( exception.getMessage());
            exception.printStackTrace();
        }
    }

    public void flush() {
        try {
            writer.flush();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void close() {
        try{
        writer.close();
        reader.close();
        socket.close();
        isAlive = false;
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public boolean isAlive() {
        return isAlive;
    }
}
