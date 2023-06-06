package org.example;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;
    String name;

    Client(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public String read() {
        String msg = "";
        boolean exit = false;
        while (!exit) {
            try {
                if (inputStream.available() > 0) {
                    int d;
                    while ((d = inputStream.read()) != 38) {
                        msg = msg + (char) d;
                    }
                    exit = true;
                }
            } catch (IOException e) {
                System.out.println("Error reading msg");
            }
        }
        return msg;
    }

    public void write(String msg) {
        try {
            outputStream.write((msg + "&").getBytes());
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("Error sending msg");
        }
    }

    public void close() {
        try {
            socket.close();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}