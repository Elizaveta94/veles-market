package com.velesmarket.service.feature.tv;

import com.velesmarket.service.feature.computer.ComputerResolutionF;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum TvResolutionF {
    HD("1366x768"),
    FULL_HD("1920x1080"),
    DCI2K("2048x1080"),
    UHD4K("3840x2160"),
    DCI4K("4096x2160"),
    UHD5K("5120x2880"),
    HSXGA("5120x4096"),
    WHSXGA("6400x4096"),
    HUXGA("6400x4800"),
    UHD8K("7680x4320");

    @Getter
    private final String resolutionName;

    TvResolutionF(String resolutionName) {
        this.resolutionName = resolutionName;
    }

    public List<String> getAllTvResolution() {
        return Arrays.stream(TvResolutionF.values()).map(TvResolutionF::getResolutionName).toList();
    }


}
