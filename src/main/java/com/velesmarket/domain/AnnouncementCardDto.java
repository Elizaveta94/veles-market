package com.velesmarket.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnnouncementCardDto {
    private long id;
    private String title;
    private int cost;
    private long photoId;
}
