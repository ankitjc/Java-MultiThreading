package org.example;

public class Main {
    public static void main(String[] args) {

        ParentThread t = new ParentThread();
        t.run();

        System.out.println("Hello world!");
    }
}