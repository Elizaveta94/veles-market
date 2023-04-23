package com.velesmarket.service.feature;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Feature {
    private FeatureType type;
    private String name;
    private List<String> values;
}
