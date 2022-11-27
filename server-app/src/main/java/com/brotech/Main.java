package com.brotech;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Server server = new Server(9090);

//        Thread service = new Thread(server);
        server.start();
    }
}