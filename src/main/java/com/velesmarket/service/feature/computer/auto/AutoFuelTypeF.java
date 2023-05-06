package com.velesmarket.service.feature.computer.auto;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum AutoFuelTypeF {
    DIESEL("Diesel"),
    PETROL("Petrol"),
    GAZ("Gaz"),
    GAZ_PETROL("Gaz + petrol");

    @Getter
    private final String fuelName;

    AutoFuelTypeF(String fuelName) {
        this.fuelName = fuelName;
    }

    public static List<String> getAllFuelTypes() {
        return Arrays.stream(AutoFuelTypeF.values()).map(AutoFuelTypeF::getFuelName).toList();
    }

}
