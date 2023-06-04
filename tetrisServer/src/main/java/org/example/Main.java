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

        Random random = new Random();
        Long seed = random.nextLong();
        int code = 100000 + random.nextInt(100000);
        System.out.println("Code: " + code);

        System.out.println("Waiting");
        socket = serverSocket.accept();
        System.out.println("Client 1 joined");
        Client client1 = new Client(socket);

        System.out.println("Waiting");
        socket = serverSocket.accept();
        System.out.println("Client 2 joined");
        Client client2 = new Client(socket);

        client1.write(String.valueOf(code));
        client2.write(String.valueOf(code));

        client1.name = client1.read();
        client2.name = client2.read();

        client1.write(String.valueOf(seed));
        client2.write(String.valueOf(seed));


        //TODO: Name from clients

        boolean client1Alive = true;
        boolean client2Alive = true;
        while (true) {
            if (client1Alive || client2Alive) {
                String fromClient1 = "";
                String fromClient2 = "";
                if (client1Alive) fromClient1 = client1.read();
                if (client2Alive) fromClient2 = client2.read();

                if (fromClient1.startsWith("gg")) {
                    System.out.println(fromClient1);
                    client2.write(fromClient1);
                    client1Alive = false;
                }
                if (fromClient2.startsWith("gg")) {
                    System.out.println(fromClient2);
                    client1.write(fromClient2);
                    client2Alive = false;
                }

                if (client1Alive) client1.write(fromClient2);
                if (client2Alive) client2.write(fromClient1);
            } else {
                //TODO: Games end logic
                break;
            }
        }
    }
}