package ru.netology;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MaxCategories { //подсчет максимальных категорий

    //метод чтения категорий продуктов из файла "categories.tsv"
    public static Map<String, String> readFromTsv(File tsvFile) {
        Map<String, String> productCategories = new HashMap<>();
        try (BufferedReader buff = new BufferedReader(new FileReader(tsvFile))) {
            while (buff.ready()) {
                String[] line = buff.readLine().split("\t");
                productCategories.put(line[0], line[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productCategories;
    }

    public static String[] category(Map<String, Integer> mapCat) {
        int maxSum = 0;
        String maxCategory = null;
        for (Map.Entry<String, Integer> entry : mapCat.entrySet())
            if (entry.getValue() > maxSum) {
                maxSum = entry.getValue();
                maxCategory = entry.getKey();
            }
        return new String[]{maxCategory, String.valueOf(maxSum)};
    }

    public static JSONObject makeJson(Map<String, Integer> mapSum) {
        String[] maxCategory = category(mapSum);
        JSONObject jsonM = new JSONObject();
        jsonM.put("category", maxCategory[0]);
        jsonM.put("sum", Integer.parseInt(maxCategory[1]));
        JSONObject jsonMax = new JSONObject();
        jsonMax.put("maxCategory", jsonM);
        return jsonMax;
    }

}
