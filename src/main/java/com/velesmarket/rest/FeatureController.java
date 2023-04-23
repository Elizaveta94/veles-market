package com.velesmarket.rest;

import com.velesmarket.service.FeatureViewService;
import com.velesmarket.service.feature.Feature;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("feature")
public class FeatureController {
    private final FeatureViewService featureViewService;

    @GetMapping("/{featureCategory}")
    public List<Feature> getFeature(@PathVariable("featureCategory") String featureCategory) {
        return featureViewService.getFeaturesView(featureCategory);
    }
}
