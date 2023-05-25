package com.velesmarket.service.mapper;

import com.velesmarket.persist.entity.AutoFeatureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface AutoFeatureMapper {

    @Mapping(target = "announcement", ignore = true)
    AutoFeatureEntity mapToEntity(Map<String, String> autoFeatureMap);

    default Map<String, String> mapToMap(AutoFeatureEntity autoFeatureEntity) {
        HashMap<String, String> map = new HashMap<>();
        map.put("fuelType", autoFeatureEntity.getFuelType());
        map.put("engineCapacity", String.valueOf(autoFeatureEntity.getEngineCapacity()));
        map.put("mileage", String.valueOf(autoFeatureEntity.getMileage()));
        map.put("autoCategory", autoFeatureEntity.getAutoCategory());
        map.put("year", String.valueOf(autoFeatureEntity.getYear()));
        map.put("brand", autoFeatureEntity.getBrand());
        map.put("model", autoFeatureEntity.getModel());
        return map;
    }

}
