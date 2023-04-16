package com.velesmarket.service.feature.computer;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ComputerBrandF {
    ACD("ACD"),
    ACER("Acer"),
    ADATA("ADATA"),
    APPLE("Apple"),
    AQUARIUS("Aquarius "),
    ARK("Ark"),
    ASUS("ASUS"),
    CHUWI("Chuwi"),
    DELL("Dell"),
    FUJITSU("Fujitsu"),
    GETAC("Getac"),
    GOOGLE("Google"),
    HAIER("Haier"),
    HASEE("Hasee"),
    HIPER("Hiper"),
    HONOR("HONOR"),
    HORIZONT("Horizont"),
    HP("HP"),
    HUAWEI("Huawei"),
    HYUNDAI("Hyundai"),
    INFINIX("Infinix"),
    ITEL("Itel"),
    LENOVO("Lenovo"),
    LG("LG"),
    MICROSOFT("Microsoft"),
    MSI("MSI"),
    PRESTIGIO("Prestigio"),
    RAXER("Razer"),
    REALME("Realme"),
    SAMSUNG("Samsung"),
    TCL("TCL"),
    TECNO("Tecno"),
    XIAOMI("Xiaomi");
    @Getter
    private final String brandName;

    ComputerBrandF(String brandName) {
        this.brandName = brandName;
    }

    public List<String> getAllComputerBrand() {
        return Arrays.stream(ComputerBrandF.values()).map(ComputerBrandF::getBrandName).toList();
    }

}
