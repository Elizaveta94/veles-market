package com.velesmarket.service.impl;

import com.velesmarket.domain.PhotoAnnouncementDto;
import com.velesmarket.persist.repository.PhotoAnnouncementRepository;
import com.velesmarket.service.PhotoAnnouncementService;
import com.velesmarket.service.mapper.PhotoAnnouncementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoAnnouncementServiceImpl implements PhotoAnnouncementService {
    private final PhotoAnnouncementRepository photoAnnouncementRepository;
    private final PhotoAnnouncementMapper mapper;

    @Override
    public PhotoAnnouncementDto get(Long id) {
        return mapper.mapToDto(photoAnnouncementRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cannot find photo by id " + id)
        ));
    }
}
