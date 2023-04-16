package com.velesmarket.service.feature.tv;

import com.velesmarket.service.feature.computer.ComputerBrandF;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum TvBrandF {
    AIWA("Aiwa"),
    AKAI("AKAI"),
    ARTEL("Artel"),
    ASANO("ASANO"),
    AVEL("AVEL"),
    BBK("BBK"),
    BLACKTON("Blackton"),
    BQ("BQ"),
    HAIER("Haier"),
    HARPER("Harper"),
    HARTENS("Hartens"),
    HISENSE("Hisense"),
    HORION("Horion"),
    HORIZONT("Horizont"),
    HUAWEI("Huawei"),
    LG("LG"),
    LOEWE("Loewe"),
    PANASONIC("Panasonic"),
    PHILIPS("Philips"),
    PRESTIGIO("Prestigio"),
    SAMSUNG("Samsung"),
    SONY("Sony"),
    TOSHIBA("Toshiba"),
    XIAOMI("Xiaomi");
    @Getter
    private final String brandName;

    TvBrandF(String brandName) {
        this.brandName = brandName;
    }

    public List<String> getAllTvBrand() {
        return Arrays.stream(TvBrandF.values()).map(TvBrandF::getBrandName).toList();
    }
}
