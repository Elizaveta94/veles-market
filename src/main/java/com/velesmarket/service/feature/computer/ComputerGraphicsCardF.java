package com.velesmarket.service.feature.computer;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ComputerGraphicsCardF {
    BUILT_IN("Built in"),
    AMD_RADEON630("AMD Radeon 630 2ГБ"),
    AMD_RADEON_PRO555X("AMD Radeon Pro 555X 4ГБ"),
    AMD_RADEON_PRO560X("AMD Radeon Pro 560X 4ГБ"),
    AMD_RADEON_PRO5300M("AMD Radeon Pro 5300M 4ГБ"),
    INTEL_HD("Intel HD"),
    INTEL_UHD("Intel UHD"),
    NVIDIA_UHD_GRAPHICS("NVIDIA UHD Graphics"),
    NVIDIA_GEFORCE_GT("NVIDIA GeForce GT"),
    NVIDIA_GEFORCE_GTX("NVIDIA GeForce GTX"),
    NVIDIA_GEFORCE_RTX("NVIDIA GeForce RTX"),
    AMD_RADEO_RX("AMD Radeion RX"),
    APPLE_M1("Apple M1 Max GPU"),
    APPLE_M1_ULTRA("Apple M1 Ultra GPU"),
    APPLE_M2("Apple M2 GPU"),
    APPLE_M2_PRO("Apple M2 Pro GPU");
    @Getter
    private final String graphicsCardName;

    ComputerGraphicsCardF(String graphicsCardName) {
        this.graphicsCardName = graphicsCardName;
    }

    public static List<String> getAllComputerGraphicsCard() {
        return Arrays.stream(ComputerGraphicsCardF.values()).map(ComputerGraphicsCardF::getGraphicsCardName).toList();
    }
}
