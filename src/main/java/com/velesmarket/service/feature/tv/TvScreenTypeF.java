package com.velesmarket.service.feature.tv;

import com.velesmarket.service.feature.computer.ComputerScreenTypeF;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum TvScreenTypeF {
    LCD("LCD"),
    CRT("CRT"),
    LED("LED"),
    PDP("PDP"),
    OLED("OLED"),
    VRD("VRD");
    @Getter
    private final String screenTypeName;

    TvScreenTypeF(String screenTypeName) {
        this.screenTypeName = screenTypeName;
    }

    public static List<String> getAllTvScreenType() {
        return Arrays.stream(TvScreenTypeF.values()).map(TvScreenTypeF::getScreenTypeName).toList();
    }
}
