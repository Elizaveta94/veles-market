package com.velesmarket.persist;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String featureTable;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
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
        CategoryEntity other = (CategoryEntity) obj;
        return Objects.equals(id, other.getId());
    }
}
