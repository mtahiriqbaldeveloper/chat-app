package com.server.chat;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {

        int port;
        if (args.length > 0)
            port = Integer.parseInt(args[0]);
        else
            port = 4444;
        Server s = new Server(port);
        s.start();
    }
}
