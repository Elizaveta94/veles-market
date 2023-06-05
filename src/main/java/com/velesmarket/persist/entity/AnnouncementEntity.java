package com.velesmarket.persist.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "announcement")
@SequenceGenerator(name = "announcement_id_seq", sequenceName = "announcement_id_seq", allocationSize = 1, initialValue = 1)
public class AnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "announcement_id_seq")
    private Long id;
    private String title;
    private String description;
    private Integer cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "announcement_id")
    @Fetch(FetchMode.SUBSELECT)
    private List<PhotoAnnouncementEntity> photosAnnouncement;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private CategoryEntity category;

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
        AnnouncementEntity other = (AnnouncementEntity) obj;
        return Objects.equals(id, other.getId());
    }

}
