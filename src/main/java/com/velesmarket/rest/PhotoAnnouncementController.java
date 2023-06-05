package com.velesmarket.rest;

import com.velesmarket.service.PhotoAnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("photo/announcement")
public class PhotoAnnouncementController {
    private final PhotoAnnouncementService photoAnnouncementService;

    @GetMapping("/{id}")
    public byte[] getPhoto(@PathVariable("id") Long photoId) {
        return photoAnnouncementService.get(photoId).getSrc();
    }
}
