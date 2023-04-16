package com.velesmarket.service.feature.computer;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ComputerProcessorF {
    INTEL_CORE2_DUO("Intel Core 2 Duo"),
    INTEL_CORE2_EXTREME("Intel Core 2 Extreme"),
    INTEL_PENTIUM("Intel Pentium Processor Extreme Edition"),
    INTEL_PENTIUM4("Intel Pentium 4"),
    INTEL_CELERON("Intel Celeron D"),
    MD_ATHLON("MD Athlon 64 FX"),
    AMD_ATHLON64("AMD Athlon 64 X2 Dual-Core"),
    INTEL_CORE_I3("Intel Core i3"),
    INTEL_CORE_I5("Intel Core i5"),
    INTEL_CORE_I7("Intel Core i7"),
    INTEL_CORE_I9("Intel Core i9"),
    AMD_SEMPRON("AMD Sempron");
    @Getter
    private final String ProcessorName;

    ComputerProcessorF(String ProcessorName) {
        this.ProcessorName = ProcessorName;
    }

    public List<String> getAllComputerProcessor() {

        return Arrays.stream(ComputerProcessorF.values()).map(ComputerProcessorF::getProcessorName).toList();
    }
}
