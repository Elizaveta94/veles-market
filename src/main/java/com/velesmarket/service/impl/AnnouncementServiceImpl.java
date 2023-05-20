package com.velesmarket.service.impl;

import com.velesmarket.domain.AnnouncementDto;
import com.velesmarket.persist.entity.AnnouncementEntity;
import com.velesmarket.persist.entity.UserEntity;
import com.velesmarket.persist.repository.AnnouncementRepository;
import com.velesmarket.persist.repository.UserRepository;
import com.velesmarket.service.AnnouncementService;
import com.velesmarket.service.mapper.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementMapper announcementMapper;
    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    @SneakyThrows
    public AnnouncementDto create(AnnouncementDto announcementDto, String userLogin) {
        UserEntity userEntity = userRepository.findByLogin(userLogin).get();
        AnnouncementEntity announcementEntity = announcementMapper.mapToEntity(announcementDto, userEntity);

        return null;
    }
}
