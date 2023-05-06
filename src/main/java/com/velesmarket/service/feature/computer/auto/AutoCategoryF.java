package com.velesmarket.service.feature.computer.auto;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum AutoCategoryF {
    PASSENGER_CAR("Passenger car"),
    TRUCK("Truck"),
    MOTORBIKE("Motorbike"),
    COACH("Coach");

    @Getter
    private final String categoryName;

    AutoCategoryF(String categoryName) {
        this.categoryName = categoryName;
    }

    public static List<String> getAllAutoCategory() {
        return Arrays.stream(AutoCategoryF.values()).map(AutoCategoryF::getCategoryName).toList();
    }
}
