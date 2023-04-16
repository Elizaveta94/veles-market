package com.velesmarket.service.feature.computer;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ComputerHardDiskF {
    GB8("8"),
    GB("16"),
    GB32("32"),
    GB64("64"),
    GB120("120"),
    GB128("128"),
    GB240("240"),
    GB250("250"),
    GB256("256"),
    GB320("320"),
    GB480("480"),
    GB500("500"),
    GB512("512"),
    GB512_32("512+32"),
    GB960("960"),
    GB1000("1000"),
    GB1024("1024"),
    GB2000("2000"),
    GB2048("2048"),
    GB4000("4000");
    @Getter
    private final String hardDiskName;

    ComputerHardDiskF(String hardDiskName) {
        this.hardDiskName = hardDiskName;
    }

    public List<String> getAllComputerHardDisk() {
        return Arrays.stream(ComputerHardDiskF.values()).map(ComputerHardDiskF::getHardDiskName).toList();
    }

}