package com.velesmarket.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoAnnouncementDto {
    private Long id;
    private byte[] src;
}
