package com.velesmarket.service.mapper;

import com.velesmarket.persist.entity.TvFeatureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface TvFeatureMapper {
    @Mapping(target = "announcement", ignore = true)
    TvFeatureEntity mapToEntity(Map<String, String> tvFeatureMap);

    default Map<String, String> mapToMap(TvFeatureEntity tvFeatureEntity) {
        HashMap<String, String> map = new HashMap<>();
        map.put("screenType", tvFeatureEntity.getScreenType());
        map.put("resolution", tvFeatureEntity.getResolution());
        map.put("smartTv", tvFeatureEntity.getSmartTv());
        map.put("refreshRate", tvFeatureEntity.getRefreshRate());
        map.put("diagonal", tvFeatureEntity.getDiagonal());
        map.put("year", String.valueOf(tvFeatureEntity.getYear()));
        map.put("brand", tvFeatureEntity.getBrand());
        map.put("model", tvFeatureEntity.getModel());
        return map;
    }
}
