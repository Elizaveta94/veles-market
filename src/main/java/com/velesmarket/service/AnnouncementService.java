package com.velesmarket.service;

import com.velesmarket.domain.AnnouncementCreateRequest;
import com.velesmarket.domain.AnnouncementDto;

public interface AnnouncementService {
    AnnouncementDto create(AnnouncementCreateRequest announcement, String userLogin);

    AnnouncementDto get(Long id);
}
