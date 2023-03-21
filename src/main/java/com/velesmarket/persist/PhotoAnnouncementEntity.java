package com.velesmarket.persist;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PhotoAnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] src;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
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

