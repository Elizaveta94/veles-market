package com.velesmarket.service.impl;

import com.velesmarket.domain.AnnouncementCardDto;
import com.velesmarket.domain.AnnouncementCreateRequest;
import com.velesmarket.domain.AnnouncementDto;
import com.velesmarket.persist.entity.*;
import com.velesmarket.persist.repository.*;
import com.velesmarket.service.AnnouncementService;
import com.velesmarket.service.mapper.AnnouncementMapper;
import com.velesmarket.service.mapper.AutoFeatureMapper;
import com.velesmarket.service.mapper.ComputerFeatureMapper;
import com.velesmarket.service.mapper.TvFeatureMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementMapper announcementMapper;
    private final AutoFeatureMapper autoFeatureMapper;
    private final TvFeatureMapper tvFeatureMapper;
    private final ComputerFeatureMapper computerFeatureMapper;

    private final AnnouncementRepository announcementRepository;
    private final PhotoAnnouncementRepository photoAnnouncementRepository;
    private final AutoFeatureRepository autoFeatureRepository;
    private final ComputerFeatureRepository computerFeatureRepository;
    private final TvFeatureRepository tvFeatureRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    @Override
    @SneakyThrows
    @Transactional
    public AnnouncementDto create(AnnouncementCreateRequest announcementCreateReq, String userLogin) {
        UserEntity userEntity = userRepository.findByLogin(userLogin).get();
        AnnouncementEntity announcementEntity = announcementMapper.mapToEntity(announcementCreateReq, userEntity);
        announcementEntity.setCategory(categoryRepository.findById(announcementEntity.getCategory().getId()).get());
        Optional<LocationEntity> existedLocation = locationRepository
                .findByCityAndStreet(announcementEntity.getLocation().getCity(), announcementEntity.getLocation().getStreet());
        existedLocation.ifPresent(announcementEntity::setLocation);
        setAnnouncementToPhotos(announcementEntity, announcementEntity.getPhotosAnnouncement());
        announcementEntity = announcementRepository.save(announcementEntity);

        switch (getFeatureCategory(announcementEntity)) {
            case "auto" -> {
                AutoFeatureEntity autoFeatureEntity = autoFeatureMapper.mapToEntity(announcementCreateReq.getFeatureMap());
                autoFeatureEntity.setAnnouncement(announcementEntity);
                autoFeatureRepository.save(autoFeatureEntity);
            }
            case "tv" -> {
                TvFeatureEntity tvFeatureEntity = tvFeatureMapper.mapToEntity(announcementCreateReq.getFeatureMap());
                tvFeatureEntity.setAnnouncement(announcementEntity);
                tvFeatureRepository.save(tvFeatureEntity);
            }
            case "computer" -> {
                ComputerFeatureEntity computerFeatureEntity = computerFeatureMapper.mapToEntity(announcementCreateReq.getFeatureMap());
                computerFeatureEntity.setAnnouncement(announcementEntity);
                computerFeatureRepository.save(computerFeatureEntity);
            }
            default -> {
            }
        }
        return get(announcementEntity.getId());
    }


    @Override
    public AnnouncementDto get(Long id) {
        AnnouncementEntity announcementEntity = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found by id: " + id));
        Map<String, String> featureMap = new HashMap<>();
        switch (getFeatureCategory(announcementEntity)) {
            case "auto" -> autoFeatureRepository.findByAnnouncementId(id).ifPresent(
                    autoFeatureEntity -> featureMap.putAll(autoFeatureMapper.mapToMap(autoFeatureEntity))
            );
            case "tv" -> tvFeatureRepository.findByAnnouncementId(id).ifPresent(
                    tvFeatureEntity -> featureMap.putAll(tvFeatureMapper.mapToMap(tvFeatureEntity))
            );
            case "computer" -> computerFeatureRepository.findByAnnouncementId(id).ifPresent(
                    computerFeatureEntity -> featureMap.putAll(computerFeatureMapper.mapToMap(computerFeatureEntity))
            );
            default -> {
            }
        }
        return announcementMapper.mapToDto(announcementEntity, featureMap);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AnnouncementEntity announcementEntity = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found by id: " + id));
        switch (getFeatureCategory(announcementEntity)) {
            case "auto" -> autoFeatureRepository.findByAnnouncementId(id).ifPresent(autoFeatureRepository::delete);
            case "tv" -> tvFeatureRepository.findByAnnouncementId(id).ifPresent(tvFeatureRepository::delete);
            case "computer" ->
                    computerFeatureRepository.findByAnnouncementId(id).ifPresent(computerFeatureRepository::delete);
            default -> {
            }
        }
        announcementRepository.delete(announcementEntity);
    }

    @Override
    public List<AnnouncementCardDto> getAllCards() {
        List<AnnouncementEntity> announcementEntities = announcementRepository.findAll();
        return announcementEntities.stream()
                .map(e -> AnnouncementCardDto.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .cost(e.getCost())
                        .photoId(e.getPhotosAnnouncement().get(0).getId())
                        .build())
                .toList();
    }

    private void setAnnouncementToPhotos(AnnouncementEntity announcementEntity,
                                         List<PhotoAnnouncementEntity> photoAnnouncementEntities) {
        if (photoAnnouncementEntities != null && !photoAnnouncementEntities.isEmpty()) {
            photoAnnouncementEntities.forEach(p -> p.setAnnouncement(announcementEntity));
        }
    }

    private String getFeatureCategory(AnnouncementEntity announcementEntity) {
        return announcementEntity.getCategory().getFeatureCategory() == null ?
                "default" :
                announcementEntity.getCategory().getFeatureCategory();
    }
}
