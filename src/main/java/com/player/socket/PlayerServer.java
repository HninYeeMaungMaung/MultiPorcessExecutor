package com.player.socket;

import java.io.*;
import java.net.*;

public class PlayerServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        long pid = ProcessHandle.current().pid();
        System.out.println("Responder started. Waiting for connection in PID: ..." + pid);

        Socket socket = serverSocket.accept();
        System.out.println("Connection established!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Player player = new Player("Responder", pid);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if ("exit".equalsIgnoreCase(inputLine)) break;
            String response = player.processMessage(inputLine);
            out.println(response);
        }

        socket.close();
        serverSocket.close();
        System.out.println("Responder shutting down.");
    }
}
