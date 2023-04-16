package com.velesmarket.persist.entity;

import javax.persistence.*;
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
@Table(name = "category")
@SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq", allocationSize = 1, initialValue = 1)
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    private Long id;
    private String title;
    private String featureTable;

    @OneToMany(mappedBy = "category")
    @Fetch(FetchMode.SUBSELECT)
    private List<AnnouncementEntity> announcements;

    @OneToMany
    @JoinColumn(name = "parent_id")
    private List<CategoryEntity> subCategories;

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
