package com.player;

import java.io.*;

public class MainMultiProcess {
    public static void main(String[] args) throws IOException, InterruptedException {
        long initiatorPid = ProcessHandle.current().pid();
        System.out.println("Initiator started in PID: " + initiatorPid);

        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/classes", "com.player.PlayerMain2", "Responder");
        pb.redirectErrorStream(true);
        Process responderProcess = pb.start();

        BufferedReader fromResponder = new BufferedReader(new InputStreamReader(responderProcess.getInputStream()));
        BufferedWriter toResponder = new BufferedWriter(new OutputStreamWriter(responderProcess.getOutputStream()));

        String startupLine = fromResponder.readLine();
        System.out.println(startupLine);

        String message = "Hello";
        int count = 0;
        final int MAX = 10;

        while (count < MAX) {
            System.out.println("Initiator " + initiatorPid + " sends: " + message);
            toResponder.write(message + "\n");
            toResponder.flush();

            String line;
            while ((line = fromResponder.readLine()) != null) {
                System.out.println("[Responder says] " + line);
                System.out.println();
                if (line.startsWith("RESPONSE:")) {
                    message = line.substring("RESPONSE:".length());
                    System.out.println("Initiator " + initiatorPid + " received: " + message);
                    break;
                }
            }
            count++;
        }

        toResponder.write("exit\n");
        toResponder.flush();
        responderProcess.waitFor();
        System.out.println("✅ Multi-process communication completed.");
    }
}
