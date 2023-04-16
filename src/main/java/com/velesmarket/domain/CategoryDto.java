package com.velesmarket.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String title;
    private String featureTable;
    private List<CategoryDto> subCategories;
}
