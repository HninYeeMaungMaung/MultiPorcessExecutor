package com.player.socket;

public class PlayerSingleProcess {
    public static void main(String[] args) {
        sameProcessID();
        System.out.println("Launcher PID: " + ProcessHandle.current().pid());
    }

    public static void sameProcessID(){

        long pid = ProcessHandle.current().pid();

        Player initiator = new Player("Initiator", pid);
        Player responder = new Player("Responder", pid);

        int rounds = 0;
        final int MAX = 10;

        String message = "Hello";

        while (rounds < MAX) {

            initiator.printPid();
            responder.printPid();
            
            message = responder.processMessage(message);
            message = initiator.processMessage(message);

            rounds++;
            System.out.println();
        }

    }
}
