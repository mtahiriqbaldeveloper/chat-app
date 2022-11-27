package com.brotech;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client{

    private int portNumber;
    private BufferedWriter writer;
    private BufferedReader inputReader;
    private Socket socket;
    private Scanner scanner;

    public Client(int portNumber) throws IOException {
        this.portNumber = portNumber;
        this.socket = new Socket("localhost",portNumber);

        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    }


    public void startWriting() {

        String message = null;
        try {
            while ((message = inputReader.readLine()) != null) {

                for(int i =0;i<message.length();i++){

                    writer.write(message);
                    writer.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //writer.close();
        //scanner.close();
    }
}
