package com.player.processBuilder;

import java.util.Scanner;

public class PlayerMain {
    public static void main(String[] args) {
        String playerName = args.length > 0 ? args[0] : "Responder";
        long pid = ProcessHandle.current().pid();
        System.out.println(playerName + " started in PID: " + pid);
        System.out.flush();

        Scanner scanner = new Scanner(System.in);
        Player player = new Player(playerName);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println(playerName + " shutting down.");
                System.out.flush();
                break;
            }
            String response = player.receiveMessage(input);
            System.out.println("RESPONSE:" + response);
            System.out.flush();
        }
    }
}
