package com.velesmarket.domain;

import lombok.Data;

import java.util.Map;

@Data
public class SearchDataDto {
    private String title;
    private Map<String, String> price;
    private Long categoryId;
    private Map<String,  Map<String, String>> featureMap;
    private Map<String, String> location;
    private int pageNumber;
}