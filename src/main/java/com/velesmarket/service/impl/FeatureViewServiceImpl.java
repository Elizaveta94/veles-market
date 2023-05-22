package com.velesmarket.service.impl;

import com.velesmarket.service.FeatureViewService;
import com.velesmarket.service.feature.Feature;
import com.velesmarket.service.feature.FeatureCategory;
import com.velesmarket.service.feature.FeatureType;
import com.velesmarket.service.feature.auto.AutoBrandF;
import com.velesmarket.service.feature.auto.AutoCategoryF;
import com.velesmarket.service.feature.auto.AutoFuelTypeF;
import com.velesmarket.service.feature.computer.*;
import com.velesmarket.service.feature.tv.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeatureViewServiceImpl implements FeatureViewService {
    private static final Map<FeatureCategory, List<Feature>> FEATURE_MAP = new HashMap<>();

    static {
        List<Feature> autoFeatures = List.of(
                Feature.builder()
                        .name("Fuel type")
                        .type(FeatureType.DROPDOWN)
                        .values(AutoFuelTypeF.getAllFuelTypes())
                        .property("fuelType")
                        .build(),
                Feature.builder()
                        .name("Engine capacity")
                        .type(FeatureType.RANGE_INPUT)
                        .property("engineCapacity")
                        .build(),
                Feature.builder()
                        .name("Mileage")
                        .type(FeatureType.RANGE_INPUT)
                        .property("mileage")
                        .build(),
                Feature.builder()
                        .name("Category")
                        .type(FeatureType.DROPDOWN)
                        .values(AutoCategoryF.getAllAutoCategory())
                        .property("autoCategory")
                        .build(),
                Feature.builder()
                        .name("Year")
                        .type(FeatureType.RANGE_INPUT)
                        .property("year")
                        .build(),
                Feature.builder()
                        .name("Brand")
                        .type(FeatureType.DROPDOWN)
                        .values(AutoBrandF.getAllAutoBrand())
                        .property("brand")
                        .build(),
                Feature.builder()
                        .name("Model")
                        .type(FeatureType.INPUT)
                        .property("model")
                        .build()
        );
        List<Feature> tvFeatures = List.of(
                Feature.builder()
                        .name("Screen type")
                        .type(FeatureType.DROPDOWN)
                        .values(TvScreenTypeF.getAllTvScreenType())
                        .property("screenType")
                        .build(),
                Feature.builder()
                        .name("Resolution")
                        .type(FeatureType.DROPDOWN)
                        .values(TvResolutionF.getAllTvResolution())
                        .property("resolution")
                        .build(),
                Feature.builder()
                        .name("Smart TV")
                        .type(FeatureType.DROPDOWN)
                        .values(TvSmartTvF.getAllTvSmartTv())
                        .property("smartTv")
                        .build(),
                Feature.builder()
                        .name("Refresh rate")
                        .type(FeatureType.DROPDOWN)
                        .values(TvRefreshRateF.getAllTvRefreshRate())
                        .property("refreshRate")
                        .build(),
                Feature.builder()
                        .name("Diagonal")
                        .type(FeatureType.DROPDOWN)
                        .values(TvDiagonalF.getAllTvDiagonal())
                        .property("diagonal")
                        .build(),
                Feature.builder()
                        .name("Year")
                        .type(FeatureType.RANGE_INPUT)
                        .property("year")
                        .build(),
                Feature.builder()
                        .name("Brand")
                        .type(FeatureType.DROPDOWN)
                        .values(TvBrandF.getAllTvBrand())
                        .property("brand")
                        .build(),
                Feature.builder()
                        .name("Model")
                        .type(FeatureType.INPUT)
                        .property("model")
                        .build()
        );
        List<Feature> computerFeatures = List.of(
                Feature.builder()
                        .name("Diagonal")
                        .type(FeatureType.DROPDOWN)
                        .values(ComputerDiagonalF.getAllComputerDiagonal())
                        .property("diagonal")
                        .build(),
                Feature.builder()
                        .name("Graphics Card")
                        .type(FeatureType.DROPDOWN)
                        .values(ComputerGraphicsCardF.getAllComputerGraphicsCard())
                        .property("graphicsCard")
                        .build(),
                Feature.builder()
                        .name("Hard disk")
                        .type(FeatureType.RANGE_INPUT)
                        .property("hardDisk")
                        .build(),
                Feature.builder()
                        .name("Hard disk type")
                        .type(FeatureType.DROPDOWN)
                        .values(ComputerHardDiskTypeF.getAllComputerHardDiskType())
                        .property("hardDiskType")
                        .build(),
                Feature.builder()
                        .name("Processor")
                        .type(FeatureType.DROPDOWN)
                        .values(ComputerHardDiskTypeF.getAllComputerHardDiskType())
                        .property("processor")
                        .build(),
                Feature.builder()
                        .name("Ram")
                        .type(FeatureType.DROPDOWN)
                        .values(ComputerRamF.getAllComputerRam())
                        .property("ram")
                        .build(),
                Feature.builder()
                        .name("Resolution")
                        .type(FeatureType.DROPDOWN)
                        .values(ComputerResolutionF.getAllComputerResolution())
                        .property("resolution")
                        .build(),
                Feature.builder()
                        .name("Screen type")
                        .type(FeatureType.DROPDOWN)
                        .values(ComputerScreenTypeF.getAllComputerScreenType())
                        .property("screenType")
                        .build(),
                Feature.builder()
                        .name("Computer type")
                        .type(FeatureType.DROPDOWN)
                        .values(ComputerTypeF.getAllComputerType())
                        .property("computerType")
                        .build(),
                Feature.builder()
                        .name("Year")
                        .type(FeatureType.RANGE_INPUT)

                        .property("year")
                        .build(),
                Feature.builder()
                        .name("Brand")
                        .type(FeatureType.DROPDOWN)
                        .values(ComputerBrandF.getAllComputerBrand())
                        .property("brand")
                        .build(),
                Feature.builder()
                        .name("Model")
                        .type(FeatureType.INPUT)
                        .property("model")
                        .build()
        );


        FEATURE_MAP.put(FeatureCategory.AUTO, autoFeatures);
        FEATURE_MAP.put(FeatureCategory.TV, tvFeatures);
        FEATURE_MAP.put(FeatureCategory.COMP, computerFeatures);
    }


    @Override
    public List<Feature> getFeaturesView(String featureCategory) {
        return FEATURE_MAP.get(FeatureCategory.byCategory(featureCategory));
    }
}
