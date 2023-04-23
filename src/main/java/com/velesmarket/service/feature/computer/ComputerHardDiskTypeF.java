package com.velesmarket.service.feature.computer;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ComputerHardDiskTypeF {
    SSD("SSD"),
    SSD_HDD("SSD+HDD"),
    HDD("HDD"),
    SSD_Intel("SSD+Intel Optane"),
    SSHD("SSHD"),
    EMMC("eMMC");
    @Getter
    private final String hardDiskTypeName;

    ComputerHardDiskTypeF(String hardDiskTypeName) {
        this.hardDiskTypeName = hardDiskTypeName;
    }

    public static List<String> getAllComputerHardDiskType() {
        return Arrays.stream(ComputerHardDiskTypeF.values()).map(ComputerHardDiskTypeF::getHardDiskTypeName).toList();
    }
}
