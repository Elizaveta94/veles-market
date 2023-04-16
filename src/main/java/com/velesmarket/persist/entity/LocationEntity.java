package com.velesmarket.persist.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "location")
@SequenceGenerator(name = "location_id_seq", sequenceName = "location_id_seq", allocationSize = 1, initialValue = 1)
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id_seq")
    private Long id;
    private String city;
    private String street;

    @OneToMany(mappedBy = "location")
    @ToString.Exclude
    private List<AnnouncementEntity> announcement;

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
        LocationEntity other = (LocationEntity) obj;
        return Objects.equals(id, other.getId());
    }

}
