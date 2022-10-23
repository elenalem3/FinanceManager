package ru.netology;

import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static final int PORT = 8989;

    public static void main(String[] args) {
        File tsvFile = new File("categories.tsv");

        Map<String, String> mapTsvFile = MaxCategories.readFromTsv(tsvFile); //считываем tsv в мап

        Set<String> valSet = new HashSet<>(mapTsvFile.values());
        Map<String, Integer> mapCat = valSet.stream()
                .collect(Collectors.toMap(x -> x, y -> 0));
        mapCat.put("другое", 0);

        try (ServerSocket serverSocket = new ServerSocket(PORT);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {  // обработка одного подключения
                    JSONObject jsOb = new JSONObject(in.readLine());
                    String title = (String) jsOb.get("title");
                    int sum = (int) jsOb.get("sum");

                    if (mapTsvFile.containsKey(title)) {
                        int oldSum = mapCat.get(mapTsvFile.get(title));
                        mapCat.replace(mapTsvFile.get(title), (oldSum + sum));
                    } else {
                        int oldSum = mapCat.get("другое");
                        mapCat.replace("другое", (oldSum + sum));
                    }

                    JSONObject jsMax = MaxCategories.makeJson(mapCat);
                    out.println(jsMax);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
