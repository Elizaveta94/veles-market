package com.velesmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDto {
    private Long id;
    private String title;
    private String description;
    private Integer cost;
    private LocationDto location;
    private CategoryDto category;
    private List<PhotoAnnouncementDto> photosAnnouncement;
}
