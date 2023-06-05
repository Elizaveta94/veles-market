package com.velesmarket.service;

import com.velesmarket.domain.AnnouncementCardDto;
import com.velesmarket.domain.AnnouncementCreateRequest;
import com.velesmarket.domain.AnnouncementDto;

import java.util.List;

public interface AnnouncementService {
    AnnouncementDto create(AnnouncementCreateRequest announcement, String userLogin);

    AnnouncementDto get(Long id);

    void delete(Long id);

    List<AnnouncementCardDto> getAllCards();
}
