package com.player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainMultiProcess2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Player initiator = new Player("Initiator");
        long initiatorPid = initiator.getPid();

        System.out.println("Initiator started in PID: " + initiatorPid);

        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/classes", "com.player.PlayerMain2", "Responder");
        pb.redirectErrorStream(true);
        Process responderProcess = pb.start();

        BufferedReader fromResponder = new BufferedReader(new InputStreamReader(responderProcess.getInputStream()));
        BufferedWriter toResponder = new BufferedWriter(new OutputStreamWriter(responderProcess.getOutputStream()));

        // Read responder startup line (includes its PID)
        String startupLine = fromResponder.readLine();
        System.out.println(startupLine);

        // Start communication loop
        String message = "Hello";
        int counter = 0;
        final int MAX = 10;

        while (counter < MAX) {
            System.out.println(initiator.getPlayerName() + " " + initiatorPid + " sends: " + message);
            toResponder.write(message + "\n");
            toResponder.flush();

            String line;
            while ((line = fromResponder.readLine()) != null) {
                System.out.println("[Responder says] " + line);
                if (line.startsWith("RESPONSE:")) {
                    String response = line.substring("RESPONSE:".length());
                    message = initiator.receiveMessage(response);  // process + return new message
                    break;
                }
            }
            counter++;
        }

        // Send shutdown command
        toResponder.write("exit\n");
        toResponder.flush();
        responderProcess.waitFor();
        System.out.println("✅ Multi-process communication completed.");
    }
}
