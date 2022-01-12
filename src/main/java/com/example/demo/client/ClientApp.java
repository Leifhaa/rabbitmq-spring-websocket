package com.example.demo.client;

import java.util.Scanner;


public class ClientApp {
    public static void main(String[] args) {
        StompClientsSimulation sim = new StompClientsSimulation();
        sim.run();

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }
}
