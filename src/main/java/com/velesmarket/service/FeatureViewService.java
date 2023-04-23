package com.velesmarket.service;

import com.velesmarket.service.feature.Feature;

import java.util.List;

public interface FeatureViewService {
    List<Feature> getFeaturesView(String featureName);
}
