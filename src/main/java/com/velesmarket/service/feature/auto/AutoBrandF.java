package com.velesmarket.service.feature.auto;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum AutoBrandF {
    ACURA("Acura"),
    ALFA_ROMEO("Alfa Romeo"),
    AUDI("Audi"),
    BMW("BMW"),
    CADILLAC("Cadillac"),
    CHERY("Chery"),
    CHEVROLET("Chevrolet"),
    CHRYSLER("Chrysler"),
    CITROEN("Citroen"),
    DACIA("Dacia"),
    DAEWOO("Daewoo"),
    DODGE("Dodge"),
    FIAT("Fiat"),
    GEELY("Geely"),
    HAVAL("Haval"),
    HONDA("Honda"),
    HUMMER("Hummer"),
    HYUNDAI("Hyundai"),
    INFINITI("Infiniti"),
    IVECO("Iveco"),
    JAC("JAC"),
    JAGUAR("Jaguar"),
    JEEP("Jeep"),
    KIA("Kia"),
    LADA("LADA"),
    LANCIA("Lancia"),
    LAND_ROVER("Land Rover"),
    LEXUS("Lexus"),
    LIFAN("Lifan"),
    MAZDA("Mazda"),
    MERCEDES_BENZ("Mercedes-Benz"),
    MERCURY("Mercury"),
    MITSUBISHI("Mitsubishi"),
    NISSAN("Nissan"),
    OPEL("Opel"),
    PEUGEOT("Peugeot"),
    PORSCHE("Porsche"),
    RAVON("Ravon"),
    RENAULT("Renault"),
    ROVER("Rover"),
    SAAB("Saab"),
    SEAT("SEAT"),
    SKODA("Skoda"),
    SMART("Smart"),
    SUBARU("Subaru"),
    SUZUKI("Suzuki"),
    TESLA("Tesla"),
    TOYOTA("Toyota"),
    VOLSKWAGEN("Volkswagen"),
    VOLVO("Volvo");

    @Getter
    private final String brandName;

    AutoBrandF(String brandName) {
        this.brandName = brandName;
    }

    public static List<String> getAllAutoBrand() {
        return Arrays.stream(AutoBrandF.values()).map(AutoBrandF::getBrandName).toList();
    }
}
