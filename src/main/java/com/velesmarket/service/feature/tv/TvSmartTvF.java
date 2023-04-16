package com.velesmarket.service.feature.tv;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum TvSmartTvF {
    LG_WEBOS("LG webOS"),
    SAMSUNG("Samsung Tizen"),
    ANDROID_TV("Android TV"),
    ANDROID("Android"),
    GOOGLE_TV("Google TV"),
    YANDEX_TV("Яндекс.ТВ"),
    PHILIPS_SAPHI("Philips Saphi"),
    PANASONIC_MY_HOME_SCREEN("Panasonic my Home Screen"),
    OPERA("Opera"),
    LINUX("Linux"),
    VIDAA("Vidaa"),
    SMART_TV("Smart TV"),
    PHILIPS("Philips Smart TV"),
    SAMSUNG_SMART("Samsung Smart Hub"),
    WILDRED("Wildred"),
    HARMONYOS("HarmonyOS");
    @Getter
    private final String smartTvName;

    TvSmartTvF(String smartTvName) {
        this.smartTvName = smartTvName;
    }

    public List<String> getAllTvSmartTv() {
        return Arrays.stream(TvSmartTvF.values()).map(TvSmartTvF::getSmartTvName).toList();
    }
}
