package ru.netology;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Buy {

    private String title;
    private LocalDate date;
    private int sum;


    public Buy(String title, String date, int sum) {
        this.title = title;
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.sum = sum;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSum() {
        return sum;
    }

    public String savingInJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Buy uploadJson(String jsonToString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonToString, Buy.class);
    }

    @Override
    public String toString() {
        return "Продукт: " + title + " Дата: " + date + " Сумма: " + sum;
    }
}
