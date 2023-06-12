package com.velesmarket.service.impl;

import com.velesmarket.domain.AnnouncementCardDto;
import com.velesmarket.domain.AnnouncementCreateRequest;
import com.velesmarket.domain.AnnouncementDto;
import com.velesmarket.domain.SearchDataDto;
import com.velesmarket.persist.entity.*;
import com.velesmarket.persist.repository.*;
import com.velesmarket.service.AnnouncementService;
import com.velesmarket.service.feature.FeatureCategory;
import com.velesmarket.service.mapper.AnnouncementMapper;
import com.velesmarket.service.mapper.AutoFeatureMapper;
import com.velesmarket.service.mapper.ComputerFeatureMapper;
import com.velesmarket.service.mapper.TvFeatureMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    public static final int PAGE_SIZE = 16;

    private final AnnouncementMapper announcementMapper;
    private final AutoFeatureMapper autoFeatureMapper;
    private final TvFeatureMapper tvFeatureMapper;
    private final ComputerFeatureMapper computerFeatureMapper;

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementCustomSearchRepository announcementSearchRepository;
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
        announcementEntity.setCategory(getCategory(announcementCreateReq));
        Optional<LocationEntity> existedLocation = locationRepository
                .findByCityAndStreet(announcementEntity.getLocation().getCity(), announcementEntity.getLocation().getStreet());
        existedLocation.ifPresent(announcementEntity::setLocation);
        setAnnouncementToPhotos(announcementEntity, announcementEntity.getPhotosAnnouncement());
        announcementEntity = announcementRepository.save(announcementEntity);

        FeatureCategory featureCategory = FeatureCategory.byCategory(getFeatureCategory(announcementEntity));
        announcementCreateReq.setFeatureMap(filterEmptyFeatures(announcementCreateReq.getFeatureMap()));
        switch (featureCategory) {
            case AUTO -> {
                AutoFeatureEntity autoFeatureEntity = autoFeatureMapper.mapToEntity(announcementCreateReq.getFeatureMap());
                autoFeatureEntity.setAnnouncement(announcementEntity);
                autoFeatureRepository.save(autoFeatureEntity);
            }
            case TV -> {
                TvFeatureEntity tvFeatureEntity = tvFeatureMapper.mapToEntity(announcementCreateReq.getFeatureMap());
                tvFeatureEntity.setAnnouncement(announcementEntity);
                tvFeatureRepository.save(tvFeatureEntity);
            }
            case COMP -> {
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
        FeatureCategory featureCategory = FeatureCategory.byCategory(getFeatureCategory(announcementEntity));
        switch (featureCategory) {
            case AUTO -> autoFeatureRepository.findByAnnouncementId(id).ifPresent(
                    autoFeatureEntity -> featureMap.putAll(autoFeatureMapper.mapToMap(autoFeatureEntity))
            );
            case TV -> tvFeatureRepository.findByAnnouncementId(id).ifPresent(
                    tvFeatureEntity -> featureMap.putAll(tvFeatureMapper.mapToMap(tvFeatureEntity))
            );
            case COMP -> computerFeatureRepository.findByAnnouncementId(id).ifPresent(
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
        FeatureCategory featureCategory = FeatureCategory.byCategory(getFeatureCategory(announcementEntity));
        switch (featureCategory) {
            case AUTO -> autoFeatureRepository.findByAnnouncementId(id).ifPresent(autoFeatureRepository::delete);
            case TV -> tvFeatureRepository.findByAnnouncementId(id).ifPresent(tvFeatureRepository::delete);
            case COMP ->
                    computerFeatureRepository.findByAnnouncementId(id).ifPresent(computerFeatureRepository::delete);
            default -> {
            }
        }
        announcementRepository.delete(announcementEntity);
    }

    @Override
    public Page<AnnouncementCardDto> getFirstPage() {
        Pageable firstPage = PageRequest.of(0, PAGE_SIZE);
        Page<AnnouncementEntity> announcementEntitiesPage = announcementRepository.findAll(firstPage);
        return announcementEntitiesPage.map(announcementMapper::mapToCardDto);
    }

    @Override
    public Page<AnnouncementCardDto> findAnnouncements(SearchDataDto searchDataDto) {
        Page<AnnouncementEntity> page = announcementSearchRepository.findAnnouncements(searchDataDto, PAGE_SIZE);
        return page.map(announcementMapper::mapToCardDto);
    }

    @Override
    public List<AnnouncementCardDto> findByUser(String userLogin) {
        UserEntity userEntity = userRepository.findByLogin(userLogin).get();
        return announcementRepository.findAllByUser(userEntity).stream().map(announcementMapper::mapToCardDto).toList();
    }

    private void setAnnouncementToPhotos(AnnouncementEntity announcementEntity,
                                         List<PhotoAnnouncementEntity> photoAnnouncementEntities) {
        if (photoAnnouncementEntities != null && !photoAnnouncementEntities.isEmpty()) {
            photoAnnouncementEntities.forEach(p -> p.setAnnouncement(announcementEntity));
        }
    }

    private String getFeatureCategory(AnnouncementEntity announcementEntity) {
        return announcementEntity.getCategory().getFeatureCategory() == null ?
                "none" :
                announcementEntity.getCategory().getFeatureCategory();
    }

    private Map<String, String> filterEmptyFeatures(Map<String, String> featureMap) {
        Map<String, String> filteredFeatureMap;
        if (featureMap == null || featureMap.isEmpty()){
            filteredFeatureMap = featureMap;
        } else {
            filteredFeatureMap = featureMap.entrySet().stream().filter(feature -> !feature.getValue().isEmpty())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        return filteredFeatureMap;
    }

    private CategoryEntity getCategory(AnnouncementCreateRequest announcementCreateReq) {
        CategoryEntity defaultCategory = categoryRepository.findByTitle("Other").get();
        CategoryEntity category;
        if (announcementCreateReq.getCategory() == null || announcementCreateReq.getCategory().getId() == null) {
            category = defaultCategory;
        } else {
            category = categoryRepository.findById(announcementCreateReq.getCategory().getId()).orElse(defaultCategory);
        }
        return category;
    }
}
