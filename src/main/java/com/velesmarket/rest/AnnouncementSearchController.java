package com.velesmarket.rest;

import com.velesmarket.domain.AnnouncementCardDto;
import com.velesmarket.domain.SearchDataDto;
import com.velesmarket.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("rest/announcement")
public class AnnouncementSearchController {
    private final AnnouncementService announcementSearchService;

    @GetMapping("/")
    public Page<AnnouncementCardDto> findAnnouncements(SearchDataDto searchDataDto) {
        Page<AnnouncementCardDto> announcementCardDtoPage = announcementSearchService.findAnnouncements(searchDataDto);
        return announcementCardDtoPage;
    }
}
