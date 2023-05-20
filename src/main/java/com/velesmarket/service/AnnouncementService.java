package com.velesmarket.service;

import com.velesmarket.domain.AnnouncementDto;

public interface AnnouncementService {
    AnnouncementDto create(AnnouncementDto announcementDto, String userLogin);
}
