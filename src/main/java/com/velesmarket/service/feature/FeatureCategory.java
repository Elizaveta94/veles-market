package com.velesmarket.service.feature;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum FeatureCategory {
    AUTO("auto"),
    TV("tv"),
    COMP("computer"),
    NONE("none");

    private final static Map<String, FeatureCategory> LOOK_UP_MAP = Arrays.stream(FeatureCategory.values())
            .collect(Collectors.toMap(FeatureCategory::getFeatureCategory, f -> f));

    @Getter
    private final String featureCategory;

    FeatureCategory(String featureCategory) {
        this.featureCategory = featureCategory;
    }

    public static FeatureCategory byCategory(String featureCategory) {
        return LOOK_UP_MAP.get(featureCategory);
    }
}
