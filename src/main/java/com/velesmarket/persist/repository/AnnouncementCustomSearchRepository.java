package com.velesmarket.persist.repository;

import com.velesmarket.domain.SearchDataDto;
import com.velesmarket.persist.entity.*;
import com.velesmarket.service.feature.FeatureCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class AnnouncementCustomSearchRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<AnnouncementEntity> findAnnouncements(SearchDataDto searchDataDto, int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AnnouncementEntity> query = criteriaBuilder.createQuery(AnnouncementEntity.class);
        Root<AnnouncementEntity> root = query.from(AnnouncementEntity.class);
        Predicate combinedPredicate = buildCombinedPredicate(criteriaBuilder, query, root, searchDataDto);
        query.select(root).where(combinedPredicate);
        List<AnnouncementEntity> resultList = entityManager.createQuery(query)
                .setFirstResult(searchDataDto.getPageNumber() * pageSize)
                .setMaxResults(pageSize).getResultList();
        Long count = count(searchDataDto);
        Pageable pageable = PageRequest.of(searchDataDto.getPageNumber(), pageSize);
        return PageableExecutionUtils.getPage(resultList, pageable, () -> count);
    }

    private Long count(SearchDataDto searchDataDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<AnnouncementEntity> root = query.from(AnnouncementEntity.class);
        Selection<Long> countSelection = criteriaBuilder.count(root);
        Predicate combinedPredicate = buildCombinedPredicate(criteriaBuilder, query, root, searchDataDto);
        query.select(countSelection).where(combinedPredicate);
        return entityManager.createQuery(query).getSingleResult();
    }

    private Predicate buildCombinedPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery query, Root<AnnouncementEntity> root, SearchDataDto searchDataDto) {
        List<Predicate> predicates = new ArrayList<>();
        if (!searchDataDto.getTitle().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + searchDataDto.getTitle().toLowerCase() + "%"));
        }
        if (!searchDataDto.getPrice().get("from").isEmpty() || !searchDataDto.getPrice().get("to").isEmpty()) {
            predicates.add(rangeNumberPredicate(criteriaBuilder, root, "cost", searchDataDto.getPrice()));
        }
        if (searchDataDto.getCategoryId() > 0) {
            predicates.add(criteriaBuilder.equal(root.get("category"), searchDataDto.getCategoryId()));
            CategoryEntity category = categoryRepository.findById(searchDataDto.getCategoryId()).get();
            if (category.getFeatureCategory() != null) {
                Predicate featurePredicate = getFeatureFilter(criteriaBuilder, query, root,
                        category.getFeatureCategory(), searchDataDto.getFeatureMap());
                predicates.add(featurePredicate);
            }
        }
        if (!searchDataDto.getLocation().get("city").isEmpty() || !searchDataDto.getLocation().get("street").isEmpty()) {
            predicates.addAll(getLocationPredicates(criteriaBuilder, root, searchDataDto.getLocation()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private List<Predicate> getLocationPredicates(CriteriaBuilder criteriaBuilder,
                                                  Root<AnnouncementEntity> root,
                                                  Map<String, String> location) {
        Join<AnnouncementEntity, LocationEntity> locationJoin = root.join("location");
        List<Predicate> predicates = new ArrayList<>();
        if (!location.get("city").isEmpty()) {
            predicates.add(criteriaBuilder.equal(locationJoin.get("city"), location.get("city")));
        }
        if (!location.get("street").isEmpty()) {
            predicates.add(criteriaBuilder.equal(locationJoin.get("street"), location.get("street")));
        }
        return predicates;
    }

    private Predicate getFeatureFilter(CriteriaBuilder criteriaBuilder,
                                       CriteriaQuery query,
                                       Root<AnnouncementEntity> root,
                                       String featureCategory,
                                       Map<String, Map<String, String>> featureMap) {
        Predicate predicate;
        switch (FeatureCategory.byCategory(featureCategory)) {
            case AUTO -> predicate = getAutoFilter(criteriaBuilder, query, root, featureMap);
            case TV -> predicate = getTvFilter(criteriaBuilder, query, root, featureMap);
            case COMP -> predicate = getComputerFilter(criteriaBuilder, query, root, featureMap);
            default -> throw new RuntimeException("Filter for " + featureCategory + " is not implemented");
        }
        return predicate;
    }

    private Predicate getAutoFilter(CriteriaBuilder criteriaBuilder,
                                    CriteriaQuery query,
                                    Root<AnnouncementEntity> root,
                                    Map<String, Map<String, String>> featureMap) {
        Subquery<Long> subQuery = query.subquery(Long.class);
        Root<AutoFeatureEntity> featureRoot = subQuery.from(AutoFeatureEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "fuelType", featureMap.get("fuelType")));
        predicates.add(rangeNumberPredicate(criteriaBuilder, featureRoot, "engineCapacity", featureMap.get("engineCapacity")));
        predicates.add(rangeNumberPredicate(criteriaBuilder, featureRoot, "mileage", featureMap.get("mileage")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "autoCategory", featureMap.get("autoCategory")));
        predicates.add(rangeNumberPredicate(criteriaBuilder, featureRoot, "year", featureMap.get("year")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "brand", featureMap.get("brand")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "model", featureMap.get("model")));
        predicates = predicates.stream().filter(Objects::nonNull).toList();
        subQuery.select(featureRoot.get("announcement"))
                .where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaBuilder.in(root.get("id")).value(subQuery);
    }

    private Predicate getTvFilter(CriteriaBuilder criteriaBuilder,
                                  CriteriaQuery query,
                                  Root<AnnouncementEntity> root,
                                  Map<String, Map<String, String>> featureMap) {
        Subquery<Long> subQuery = query.subquery(Long.class);
        Root<TvFeatureEntity> featureRoot = subQuery.from(TvFeatureEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "screenType", featureMap.get("screenType")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "resolution", featureMap.get("resolution")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "smartTv", featureMap.get("smartTv")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "refreshRate", featureMap.get("refreshRate")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "diagonal", featureMap.get("diagonal")));
        predicates.add(rangeNumberPredicate(criteriaBuilder, featureRoot, "year", featureMap.get("year")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "brand", featureMap.get("brand")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "model", featureMap.get("model")));
        predicates = predicates.stream().filter(Objects::nonNull).toList();
        subQuery.select(featureRoot.get("announcement"))
                .where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaBuilder.in(root.get("id")).value(subQuery);
    }

    private Predicate getComputerFilter(CriteriaBuilder criteriaBuilder,
                                        CriteriaQuery query,
                                        Root<AnnouncementEntity> root,
                                        Map<String, Map<String, String>> featureMap) {
        Subquery<Long> subQuery = query.subquery(Long.class);
        Root<ComputerFeatureEntity> featureRoot = subQuery.from(ComputerFeatureEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "diagonal", featureMap.get("diagonal")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "graphicsCard", featureMap.get("graphicsCard")));
        predicates.add(rangeNumberPredicate(criteriaBuilder, featureRoot, "hardDisk", featureMap.get("hardDisk")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "hardDiskType", featureMap.get("hardDiskType")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "processor", featureMap.get("processor")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "ram", featureMap.get("ram")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "resolution", featureMap.get("resolution")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "screenType", featureMap.get("screenType")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "computerType", featureMap.get("computerType")));
        predicates.add(rangeNumberPredicate(criteriaBuilder, featureRoot, "year", featureMap.get("year")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "brand", featureMap.get("brand")));
        predicates.add(valuePredicate(criteriaBuilder, featureRoot, "model", featureMap.get("model")));
        predicates = predicates.stream().filter(Objects::nonNull).toList();
        subQuery.select(featureRoot.get("announcement"))
                .where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaBuilder.in(root.get("id")).value(subQuery);
    }

    private Predicate rangeNumberPredicate(CriteriaBuilder criteriaBuilder, Root root, String property, Map<String, String> range) {
        if (!range.get("from").isEmpty() || !range.get("to").isEmpty()) {
            int from = range.get("from").isEmpty() ? 0 : Integer.parseInt(range.get("from"));
            int to = range.get("to").isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(range.get("to"));
            return criteriaBuilder.between(root.get(property), from, to);
        } else {
            return null;
        }
    }

    private Predicate valuePredicate(CriteriaBuilder criteriaBuilder, Root root, String property, Map<String, String> value) {
        if (!value.get("value").isEmpty()) {
            String model = value.get("value");
            return criteriaBuilder.equal(root.get(property), model);
        } else {
            return null;
        }
    }
}
