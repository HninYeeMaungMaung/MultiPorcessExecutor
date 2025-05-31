package com.player;

import java.util.Scanner;

public class MainSingleProcess {
    public static void main(String[] args) {
        sameProcessID();
        System.out.println("Launcher PID: " + ProcessHandle.current().pid());
    }

    public static void sameProcessID(){

        Player initiator = new Player("Initiator");
        Player responder = new Player("Responder");

        int rounds = 0;
        final int MAX = 10;

        String message = initiator.sendMessage();

        while (rounds < MAX) {

            initiator.printPid();
            responder.printPid();
            
            message = responder.receiveMessage(message);
            message = initiator.receiveMessage(message);

            rounds++;
            System.out.println();
        }

    }
}
