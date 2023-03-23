package com.velesmarket.persist;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TvFeature")
public class TvFeatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String screenType;
    private String resolution;
    private String smartTv;
    private String refreshRate;
    private String diagonal;
    private int year;
    private String brand;
    private String model;

    @OneToOne
    @JoinColumn(name = "AnnouncementId")
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
