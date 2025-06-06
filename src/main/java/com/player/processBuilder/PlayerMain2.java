package com.player.processBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerMain2 {
    public static void main(String[] args) {
        String playerName = args.length > 0 ? args[0] : "Responder";
        long pid = ProcessHandle.current().pid();
        System.out.println(playerName + " started in PID: " + pid);
        System.out.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Player player = new Player(playerName);

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line.trim())) {
                    System.out.println(playerName + " shutting down.");
                    System.out.flush();
                    break;
                }

                String response = player.receiveMessage(line);
                System.out.println("RESPONSE:" + response);
                System.out.flush();
            }
        } catch (IOException e) {
            System.err.println(playerName + " encountered an error reading input: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
