package com.player.socket;

public class Player {
    private final String name;
    private int counter = 0;
    private final long pid;

    public Player(String name, long pid) {
        this.name = name;
        this.pid = pid;
    }

    public String processMessage(String message) {
        counter++;
        System.out.println(name + " received: " + message);
        String response = message + "-" + counter;
        System.out.println(name + " replies: " + response);
        return response;
    }

    public String getName() {
        return name;
    }

    public void printPid() {
        System.out.println(name + " is running in PID: " + pid);
    }

    public long getPid() {
        return pid;
    }
}
