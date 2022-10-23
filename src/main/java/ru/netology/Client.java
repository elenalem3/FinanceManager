package ru.netology;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final int PORT = 8989;
    private static final String LOCALHOST = "127.0.0.1";

    public static void main(String[] args) {

        try (Socket clientSocket = new Socket(LOCALHOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            JSONObject request = new JSONObject(
                    "{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}");

            out.println(request);
            System.out.println(in.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
