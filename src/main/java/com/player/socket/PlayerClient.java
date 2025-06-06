package com.player.socket;

import java.io.*;
import java.net.*;

public class PlayerClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        long pid = ProcessHandle.current().pid();
        System.out.println("Initiator started in PID: ..." + pid);
        System.out.println("Initiator connected to Responder.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        Player player = new Player("Initiator", pid);
        String message = "Hello";

        for (int i = 0; i < 10; i++) {
            System.out.println("Initiator sends: " + message);
            out.println(message);
            String response = in.readLine();
            message = player.processMessage(response);
        }

        out.println("exit");
        socket.close();
        System.out.println("Initiator finished communication.");
    }
}
