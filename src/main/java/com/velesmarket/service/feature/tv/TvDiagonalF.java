package com.velesmarket.service.feature.tv;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum TvDiagonalF {
    D22("22"),
    D24_28("24-28"),
    D32("32"),
    D40("40"),
    D42("42"),
    D43("43"),
    D50("50"),
    D55("55"),
    D58_60("58-60"),
    D65("65"),
    D70("70"),
    D75("75");
    @Getter
    private final String diagonalName;

    TvDiagonalF(String diagonalName) {
        this.diagonalName = diagonalName;
    }

    public static List<String> getAllTvDiagonal() {
        return Arrays.stream(TvDiagonalF.values()).map(TvDiagonalF::getDiagonalName).toList();
    }
}
