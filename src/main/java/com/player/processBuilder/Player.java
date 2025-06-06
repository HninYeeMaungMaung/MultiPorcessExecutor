package com.player.processBuilder;

public class Player {

    private String playerName = "";
    private final long pid;
    private int counter = 0;

    public Player(){
       pid = ProcessHandle.current().pid();
    }

    public Player(String playerName){
        this();
        this.playerName = playerName;
    }

    public String sendMessage() {
        String message = "Hello";
        System.out.println(playerName + " sends message: " + message);
        return message;
    }

    public String receiveMessage(String message) {
        counter++;
        String response = message + "-" + counter;
        System.out.println(playerName + " received: " + message);
        System.out.println(playerName + " replies: " + response);
        return response;
    }

    public int getCounter() {
        return counter;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void printPid() {
        System.out.println(playerName + " is running in PID: " + pid);
    }

    public long getPid() {
        return pid;
    }

}
