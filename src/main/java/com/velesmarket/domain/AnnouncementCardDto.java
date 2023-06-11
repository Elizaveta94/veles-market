package com.velesmarket.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnnouncementCardDto {
    private Long id;
    private String title;
    private Integer cost;
    private Long photoId;
}
