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
@Table(name = "auto_feature")
@SequenceGenerator(name = "auto_feature_id_seq", sequenceName = "auto_feature_id_seq", allocationSize = 1, initialValue = 1)
public class AutoFeatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_feature_id_seq")
    private Long id;
    private String fuelType;
    private Integer engineCapacity;
    private Integer mileage;
    private String autoCategory;
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
        AutoFeatureEntity other = (AutoFeatureEntity) obj;
        return Objects.equals(id, other.getId());
    }
}
