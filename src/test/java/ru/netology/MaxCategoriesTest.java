package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MaxCategoriesTest {
    Map<String, Integer> mapCat = new HashMap<>();

    @BeforeEach
    void setUp() {
        mapCat.put("еда", 120);
        mapCat.put("одежда", 5000);
        mapCat.put("финансы", 10);
    }

    @DisplayName("Тест на поиск максимума")
    @Test
    void max() {
        Assertions.assertTrue(Objects.deepEquals(new String[]{"одежда", "5000"}, MaxCategories.category(mapCat)));
    }

    @DisplayName("Тест на отсутствие принадлежности к максимуму")
    @Test
    void notMax1() {
        Assertions.assertFalse(Objects.deepEquals(new String[]{"еда", "120"}, MaxCategories.category(mapCat)));
    }

    @DisplayName("Тест на отсутствие принадлежности к максимуму")
    @Test
    void notMax2() {
        Assertions.assertFalse(Objects.deepEquals(new String[]{"финансы", "10"}, MaxCategories.category(mapCat)));
    }
}
