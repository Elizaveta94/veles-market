package com.velesmarket.service.feature.tv;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum TvRefreshRateF {
    HZ60("60 Hz"),
    HZ120("120 Hz"),
    HZ144("144 Hz");
    @Getter
    private final String refreshRateName;

    TvRefreshRateF(String refreshRateName) {
        this.refreshRateName = refreshRateName;
    }

    public List<String> getAllTvRefreshRate() {
        return Arrays.stream(TvRefreshRateF.values()).map(TvRefreshRateF::getRefreshRateName).toList();
    }
}
