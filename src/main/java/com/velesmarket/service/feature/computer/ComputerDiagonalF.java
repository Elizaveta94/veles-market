package com.velesmarket.service.feature.computer;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ComputerDiagonalF {
    D13_14("13-14"),
    D15("15"),
    D17("17"),
    D19("19"),
    D21("21"),
    D22("22"),
    D24("24"),
    D27("27"),
    D28_31("28-32"),
    D32("32"),
    D34("34");
    @Getter
    private final String diagonalName;

    ComputerDiagonalF(String diagonalName) {
        this.diagonalName = diagonalName;
    }

    public static List<String> getAllComputerDiagonal() {
        return Arrays.stream(ComputerDiagonalF.values()).map(ComputerDiagonalF::getDiagonalName).toList();
    }
}
