package com.velesmarket.persist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "photo_announcement")
@SequenceGenerator(name = "photo_announcement_id_seq", sequenceName = "photo_announcement_id_seq", allocationSize = 1, initialValue = 1)
public class PhotoAnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_announcement_id_seq")
    private Long id;
    private byte[] src;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id")
    @ToString.Exclude
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
        PhotoAnnouncementEntity other = (PhotoAnnouncementEntity) obj;
        return Objects.equals(id, other.getId());
    }
}

