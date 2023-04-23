package com.velesmarket.service.feature.computer;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ComputerTypeF {
    LAPTOP("Laptop"),
    DESKTOP_COMPUTER("Desktop computer");
    @Getter
    private final String computerTypeName;

    ComputerTypeF(String computerTypeName) {
        this.computerTypeName = computerTypeName;
    }

    public static List<String> getAllComputerType() {
        return Arrays.stream(ComputerTypeF.values()).map(ComputerTypeF::getComputerTypeName).toList();
    }
}
