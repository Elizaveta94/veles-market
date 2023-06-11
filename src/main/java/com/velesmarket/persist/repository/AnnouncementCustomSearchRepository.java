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
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + searchDataDto.getTitle() + "%"));
        }
        if (!searchDataDto.getPrice().get("from").isEmpty()) {
            predicates.add(getPricePredicate(criteriaBuilder, root, searchDataDto.getPrice()));
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

    private Predicate getPricePredicate(CriteriaBuilder criteriaBuilder,
                                        Root<AnnouncementEntity> root,
                                        Map<String, String> price) {
        int from = Integer.parseInt(price.get("from"));
        int to = Integer.parseInt(price.get("to"));
        return criteriaBuilder.between(root.get("cost"), from, to);
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
        if (!featureMap.get("fuelType").get("value").isEmpty()) {
            String fuelType = featureMap.get("fuelType").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("fuelType"), fuelType));
        }
        if (!featureMap.get("engineCapacity").get("from").isEmpty()) {
            int from = Integer.parseInt(featureMap.get("engineCapacity").get("from"));
            int to = Integer.parseInt(featureMap.get("engineCapacity").get("to"));
            predicates.add(criteriaBuilder.between(featureRoot.get("engineCapacity"), from, to));
        }
        if (!featureMap.get("mileage").get("from").isEmpty()) {
            int from = Integer.parseInt(featureMap.get("mileage").get("from"));
            int to = Integer.parseInt(featureMap.get("mileage").get("to"));
            predicates.add(criteriaBuilder.between(featureRoot.get("mileage"), from, to));
        }
        if (!featureMap.get("autoCategory").get("value").isEmpty()) {
            String autoCategory = featureMap.get("autoCategory").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("autoCategory"), autoCategory));
        }
        if (!featureMap.get("year").get("from").isEmpty()) {
            int from = Integer.parseInt(featureMap.get("year").get("from"));
            int to = Integer.parseInt(featureMap.get("year").get("to"));
            predicates.add(criteriaBuilder.between(featureRoot.get("year"), from, to));
        }
        if (!featureMap.get("brand").get("value").isEmpty()) {
            String brand = featureMap.get("brand").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("brand"), brand));
        }
        if (!featureMap.get("model").get("value").isEmpty()) {
            String model = featureMap.get("model").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("model"), model));
        }
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
        if (!featureMap.get("screenType").get("value").isEmpty()) {
            String screenType = featureMap.get("screenType").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("screenType"), screenType));
        }
        if (!featureMap.get("resolution").get("value").isEmpty()) {
            String resolution = featureMap.get("resolution").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("resolution"), resolution));
        }
        if (!featureMap.get("smartTv").get("value").isEmpty()) {
            String smartTv = featureMap.get("smartTv").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("smartTv"), smartTv));
        }
        if (!featureMap.get("refreshRate").get("value").isEmpty()) {
            String refreshRate = featureMap.get("refreshRate").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("refreshRate"), refreshRate));
        }
        if (!featureMap.get("diagonal").get("value").isEmpty()) {
            String diagonal = featureMap.get("diagonal").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("diagonal"), diagonal));
        }
        if (!featureMap.get("year").get("from").isEmpty()) {
            int from = Integer.parseInt(featureMap.get("year").get("from"));
            int to = Integer.parseInt(featureMap.get("year").get("to"));
            predicates.add(criteriaBuilder.between(featureRoot.get("year"), from, to));
        }
        if (!featureMap.get("brand").get("value").isEmpty()) {
            String brand = featureMap.get("brand").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("brand"), brand));
        }
        if (!featureMap.get("model").get("value").isEmpty()) {
            String model = featureMap.get("model").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("model"), model));
        }
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
        if (!featureMap.get("diagonal").get("value").isEmpty()) {
            String diagonal = featureMap.get("diagonal").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("diagonal"), diagonal));
        }
        if (!featureMap.get("graphicsCard").get("value").isEmpty()) {
            String graphicsCard = featureMap.get("graphicsCard").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("graphicsCard"), graphicsCard));
        }
        if (!featureMap.get("hardDisk").get("from").isEmpty()) {
            int from = Integer.parseInt(featureMap.get("year").get("from"));
            int to = Integer.parseInt(featureMap.get("year").get("to"));
            predicates.add(criteriaBuilder.between(featureRoot.get("hardDisk"), from, to));
        }
        if (!featureMap.get("hardDiskType").get("value").isEmpty()) {
            String hardDiskType = featureMap.get("hardDiskType").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("hardDiskType"), hardDiskType));
        }
        if (!featureMap.get("processor").get("value").isEmpty()) {
            String processor = featureMap.get("processor").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("processor"), processor));
        }
        if (!featureMap.get("ram").get("value").isEmpty()) {
            String ram = featureMap.get("ram").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("ram"), ram));
        }
        if (!featureMap.get("resolution").get("value").isEmpty()) {
            String resolution = featureMap.get("resolution").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("resolution"), resolution));
        }
        if (!featureMap.get("screenType").get("value").isEmpty()) {
            String screenType = featureMap.get("screenType").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("screenType"), screenType));
        }
        if (!featureMap.get("computerType").get("value").isEmpty()) {
            String computerType = featureMap.get("computerType").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("computerType"), computerType));
        }
        if (!featureMap.get("year").get("from").isEmpty()) {
            int from = Integer.parseInt(featureMap.get("year").get("from"));
            int to = Integer.parseInt(featureMap.get("year").get("to"));
            predicates.add(criteriaBuilder.between(featureRoot.get("year"), from, to));
        }
        if (!featureMap.get("brand").get("value").isEmpty()) {
            String brand = featureMap.get("brand").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("brand"), brand));
        }
        if (!featureMap.get("model").get("value").isEmpty()) {
            String model = featureMap.get("model").get("value");
            predicates.add(criteriaBuilder.equal(featureRoot.get("model"), model));
        }
        subQuery.select(featureRoot.get("announcement"))
                .where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaBuilder.in(root.get("id")).value(subQuery);
    }
}
