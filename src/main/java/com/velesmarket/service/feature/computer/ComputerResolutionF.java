package com.velesmarket.service.feature.computer;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ComputerResolutionF {
    HD("1366x768"),
    SXGA("1280x768"),
    CIF("1408x152"),
    WXGA("1440x900"),
    HDV_1080i("1400x10800"),
    XJXGA("1536x960"),
    WSXGA("1600x1024"),
    UXGA("1600x1200"),
    FULL_HD("1920x1080"),
    WUXGA("1920x1200"),
    DCI2K("2048x1080"),
    QXGA("2048x1536"),
    UWHD("2560x1080"),
    WQXGA("2560x1600"),
    QSXGA("2560x2048"),
    WQSXGA("3200x2048"),
    QUXGA("3200x2400"),
    ULTRA_WQHD("U3440x1440"),
    WQUXGA("3840x2400"),
    UHD4K("3840x2160"),
    DCI4K("4096x2160"),
    UHD5K("5120x2880"),
    HSXGA("5120x4096"),
    WHSXGA("6400x4096"),
    HUXGA("6400x4800"),
    UHD8K("7680x4320");

    @Getter
    private final String resolutionName;

    ComputerResolutionF(String resolutionName) {
        this.resolutionName = resolutionName;
    }

    public List<String> getAllComputerResolution() {
        return Arrays.stream(ComputerResolutionF.values()).map(ComputerResolutionF::getResolutionName).toList();
    }

}
