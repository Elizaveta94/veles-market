package com.velesmarket.service.feature.computer;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ComputerRamF {
    GB2("2 GB"),
    GB4("4 GB"),
    GB8("8 GB"),
    GB16("16 GB"),
    GB24("24 GB"),
    GB32("32 GB");
    @Getter
    private final String ramName;

    ComputerRamF(String ramName) {
        this.ramName = ramName;
    }

    public List<String> getAllComputerRam() {
        return Arrays.stream(ComputerRamF.values()).map(ComputerRamF::getRamName).toList();
    }
}
