package com.velesmarket.service.feature.computer;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ComputerScreenTypeF {
    LCD("LCD"),
    CRT("CRT"),
    LED("LED"),
    PDP("PDP"),
    OLED("OLED"),
    VRD("VRD");
    @Getter
    private final String screenTypeName;

    ComputerScreenTypeF(String screenTypeName) {
        this.screenTypeName = screenTypeName;
    }

    public List<String> getAllComputerScreenType() {
        return Arrays.stream(ComputerScreenTypeF.values()).map(ComputerScreenTypeF::getScreenTypeName).toList();
    }
}
