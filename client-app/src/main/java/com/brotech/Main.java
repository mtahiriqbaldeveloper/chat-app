package com.brotech;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Client client =  new Client(9090);
//        Thread service = new Thread(client);
        client.startWriting();
    }
}