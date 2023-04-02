package com.velesmarket.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String title;
    private String featureTable;
    private CategoryDto parent;
}
