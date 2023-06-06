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
        int code;

        code = 100000 + random.nextInt(100000);
        System.out.println("Код доступа (полуфинал): " + code);

        System.out.println("Ожидание игрока 1..");
        socket = serverSocket.accept();
        System.out.println("Игрок 1 подключился");
        Client client1 = new Client(socket);

        System.out.println("Ожидание игрока 2..");
        socket = serverSocket.accept();
        System.out.println("Игрок 2 подключился");
        Client client2 = new Client(socket);

        System.out.println("Ожидание игрока 3..");
        socket = serverSocket.accept();
        System.out.println("Игрок 3 подключился");
        Client client3 = new Client(socket);

        System.out.println("Ожидание игрока 4..");
        socket = serverSocket.accept();
        System.out.println("Игрок 4 подключился");
        Client client4 = new Client(socket);

        Thread game1 = new Thread(new Runnable() {
            @Override
            public void run() {
                client1.write(String.valueOf(code));
                client2.write(String.valueOf(code));

                client1.name = client1.read();
                client2.name = client2.read();

                client1.write(String.valueOf(seed));
                client2.write(String.valueOf(seed));

                boolean client1Alive = true;
                boolean client2Alive = true;
                long client1FinalScore = 0;
                long client2FinalScore = 0;

                while (true) {
                    if (client1Alive || client2Alive) {
                        String fromClient1 = "";
                        String fromClient2 = "";
                        if (client1Alive) fromClient1 = client1.read();
                        if (client2Alive) fromClient2 = client2.read();

                        if (fromClient1.startsWith("gg")) {
                            client2.write(fromClient1);
                            client1Alive = false;
                            client1FinalScore = Long.parseLong(fromClient1.substring(2));
                        }
                        if (fromClient2.startsWith("gg")) {
                            client1.write(fromClient2);
                            client2Alive = false;
                            client2FinalScore = Long.parseLong(fromClient2.substring(2));
                        }

                        if (client1Alive) client1.write(fromClient2);
                        if (client2Alive) client2.write(fromClient1);
                    } else {
                        if (client1FinalScore > client2FinalScore) {
                            System.out.println(client1.name + " победил в полуфинале с очками: " + client1FinalScore);
                        } else if (client1FinalScore < client2FinalScore) {
                            System.out.println(client2.name + " победил в полуфинале с очками: " + client2FinalScore);
                        }
                        break;
                    }
                }
            }
        });
        game1.start();

        Thread game2 = new Thread(new Runnable() {
            @Override
            public void run() {
                client3.write(String.valueOf(code));
                client4.write(String.valueOf(code));

                client3.name = client3.read();
                client4.name = client4.read();

                client3.write(String.valueOf(seed));
                client4.write(String.valueOf(seed));

                boolean client3Alive = true;
                boolean client4Alive = true;
                long client3FinalScore = 0;
                long client4FinalScore = 0;

                while (true) {
                    if (client3Alive || client4Alive) {
                        String fromClient3 = "";
                        String fromClient4 = "";
                        if (client3Alive) fromClient3 = client3.read();
                        if (client4Alive) fromClient4 = client4.read();

                        if (fromClient3.startsWith("gg")) {
                            client4.write(fromClient3);
                            client3Alive = false;
                            client3FinalScore = Long.parseLong(fromClient3.substring(2));
                        }
                        if (fromClient4.startsWith("gg")) {
                            client3.write(fromClient4);
                            client4Alive = false;
                            client4FinalScore = Long.parseLong(fromClient4.substring(2));
                        }

                        if (client3Alive) client3.write(fromClient4);
                        if (client4Alive) client4.write(fromClient3);
                    } else {
                        if (client3FinalScore > client4FinalScore) {
                            System.out.println(client3.name + " победил в полуфинале с очками: " + client3FinalScore);
                        } else if (client3FinalScore < client4FinalScore) {
                            System.out.println(client4.name + " победил в полуфинале с очками: " + client4FinalScore);
                        }
                        break;
                    }
                }
            }
        });
        game2.start();

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите start, чтобы начать финал");
            if (scanner.nextLine().equals("start")) break;
        }


        System.out.println("Ожидание игрока 5..");
        socket = serverSocket.accept();
        System.out.println("Игрок 5 подключился");
        Client client5 = new Client(socket);

        System.out.println("Ожидание игрока 6..");
        socket = serverSocket.accept();
        System.out.println("Игрок 6 подключился");
        Client client6 = new Client(socket);

        Thread game3 = new Thread(new Runnable() {
            @Override
            public void run() {
                int code2 = 100000 + random.nextInt(100000);
                System.out.println("Код доступа (финал): " + code2);

                client5.write(String.valueOf(code2));
                client6.write(String.valueOf(code2));


                client5.name = client5.read();
                client6.name = client6.read();

                client5.write(String.valueOf(seed));
                client6.write(String.valueOf(seed));

                boolean client5Alive = true;
                boolean client6Alive = true;
                long client5FinalScore = 0;
                long client6FinalScore = 0;

                while (true) {
                    if (client5Alive || client6Alive) {
                        String fromClient5 = "";
                        String fromClient6 = "";
                        if (client5Alive) fromClient5 = client5.read();
                        if (client6Alive) fromClient6 = client6.read();

                        if (fromClient5.startsWith("gg")) {
                            client6.write(fromClient5);
                            client5Alive = false;
                            client5FinalScore = Long.parseLong(fromClient5.substring(2));
                        }
                        if (fromClient6.startsWith("gg")) {
                            client5.write(fromClient6);
                            client6Alive = false;
                            client6FinalScore = Long.parseLong(fromClient6.substring(2));
                        }

                        if (client5Alive) client5.write(fromClient6);
                        if (client6Alive) client6.write(fromClient5);
                    } else {
                        if (client5FinalScore > client6FinalScore) {
                            System.out.println(client5.name + " победил в финале с очками: " + client5FinalScore);
                        } else if (client5FinalScore < client6FinalScore) {
                            System.out.println(client6.name + " победил в полуфинале с очками: " + client6FinalScore);
                        }
                        break;
                    }
                }
            }
        });
        game3.start();
    }
}
