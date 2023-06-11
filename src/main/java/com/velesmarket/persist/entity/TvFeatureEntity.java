package com.velesmarket.persist.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tv_feature")
@SequenceGenerator(name = "tv_feature_id_seq", sequenceName = "tv_feature_id_seq", allocationSize = 1, initialValue = 1)
public class TvFeatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tv_feature_id_seq")
    private Long id;
    private String screenType;
    private String resolution;
    private String smartTv;
    private String refreshRate;
    private String diagonal;
    private Integer year;
    private String brand;
    private String model;

    @OneToOne
    @JoinColumn(name = "announcement_id")
    private AnnouncementEntity announcement;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TvFeatureEntity other = (TvFeatureEntity) obj;
        return Objects.equals(id, other.getId());
    }
}
