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
@Table(name = "AutoFeature")
public class AutoFeatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fuelType;
    private String engineCapacity;
    private String mileage;
    private String autoCategory;
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
        AutoFeatureEntity other = (AutoFeatureEntity) obj;
        return Objects.equals(id, other.getId());
    }
}
