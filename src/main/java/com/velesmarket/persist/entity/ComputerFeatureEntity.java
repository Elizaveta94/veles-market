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
@Table(name = "computer_feature")
@SequenceGenerator(name = "computer_feature_id_seq", sequenceName = "computer_feature_id_seq", allocationSize = 1, initialValue = 1)
public class ComputerFeatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "computer_feature_id_seq")
    private Long id;
    private String screenType;
    private String resolution;
    private String ram;
    private String processor;
    private String diagonal;
    private String computerType;
    private String graphicsCard;
    private String hardDisk;
    private String hardDiskType;
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
        ComputerFeatureEntity other = (ComputerFeatureEntity) obj;
        return Objects.equals(id, other.getId());
    }
}
