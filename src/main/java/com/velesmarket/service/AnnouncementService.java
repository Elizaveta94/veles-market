package com.velesmarket.service;

import com.velesmarket.domain.AnnouncementCardDto;
import com.velesmarket.domain.AnnouncementCreateRequest;
import com.velesmarket.domain.AnnouncementDto;
import com.velesmarket.domain.SearchDataDto;
import org.springframework.data.domain.Page;

public interface AnnouncementService {
    AnnouncementDto create(AnnouncementCreateRequest announcement, String userLogin);

    AnnouncementDto get(Long id);

    void delete(Long id);

    Page<AnnouncementCardDto> getFirstPage();

    Page<AnnouncementCardDto> findAnnouncements(SearchDataDto searchDataDto);

}
