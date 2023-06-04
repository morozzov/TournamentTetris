package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1024);
        Socket socket = null;
        System.out.println("Waiting");
        socket = serverSocket.accept();
        System.out.println("Client 1 joined");
        Client client1 = new Client(socket);

        socket = serverSocket.accept();
        System.out.println("Client 2 joined");
        Client client2 = new Client(socket);

        Random random = new Random();
        Long seed = random.nextLong();

        client1.write(String.valueOf(seed));
        client2.write(String.valueOf(seed));


        System.out.println("Waiting");
        socket = serverSocket.accept();
        System.out.println("Client 1 joined");
        client1 = new Client(socket);

        socket = serverSocket.accept();
        System.out.println("Client 2 joined");
        client2 = new Client(socket);

        //TODO: Name from clients
        while (true) {
            String fromClient1 = client1.read();
            String fromClient2 = client2.read();

            client1.write(fromClient2);
            client2.write(fromClient1);
//            System.out.println("Client1: "+fromClient1);
//            System.out.println("Client2: "+fromClient2);
        }
    }
}