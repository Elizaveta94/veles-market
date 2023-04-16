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
@Setter
@Getter
@NoArgsConstructor
@Table(name = "veles_user")
@SequenceGenerator(name = "veles_user_id_seq", sequenceName = "veles_user_id_seq", allocationSize = 1, initialValue = 1)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veles_user_id_seq")
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String login;
    private String password;
    private Long mobileNumber;
    private byte[] photo;

    @OneToMany(mappedBy = "user")
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
        UserEntity other = (UserEntity) obj;
        return Objects.equals(id, other.getId());
    }
}
