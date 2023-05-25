package com.velesmarket.service.mapper;

import com.velesmarket.persist.entity.AutoFeatureEntity;
import com.velesmarket.persist.entity.ComputerFeatureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ComputerFeatureMapper {
    @Mapping(target = "announcement", ignore = true)
    ComputerFeatureEntity mapToEntity(Map<String, String> computerFeatureMap);

    default Map<String, String> mapToMap(ComputerFeatureEntity computerFeatureEntity) {
        HashMap<String, String> map = new HashMap<>();
        map.put("screenType", computerFeatureEntity.getScreenType());
        map.put("resolution", computerFeatureEntity.getResolution());
        map.put("ram", computerFeatureEntity.getRam());
        map.put("processor", computerFeatureEntity.getProcessor());
        map.put("diagonal", computerFeatureEntity.getDiagonal());
        map.put("computerType", computerFeatureEntity.getComputerType());
        map.put("graphicsCard", computerFeatureEntity.getGraphicsCard());
        map.put("hardDisk", computerFeatureEntity.getHardDisk());
        map.put("hardDiskType", computerFeatureEntity.getHardDiskType());
        map.put("year", String.valueOf(computerFeatureEntity.getYear()));
        map.put("brand", computerFeatureEntity.getBrand());
        map.put("model", computerFeatureEntity.getModel());
        return map;
    }
}

