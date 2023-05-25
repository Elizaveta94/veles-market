package com.velesmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementCreateRequest {
    private Long id;
    private String title;
    private String description;
    private Integer cost;
    private LocationDto location;
    private CategoryDto category;
    private List<MultipartFile> photosAnnouncement;
    private Map<String, String> featureMap;
}
